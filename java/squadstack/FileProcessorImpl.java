package squadstack;

import squadstack.constants.InputFileConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class FileProcessorImpl implements FileProcessor {

    @Override
    public void process(String fileName) {

        File file = new File(fileName);
//        File outputFile = new File("output.txt");

        try (FileInputStream fileInputStream = new FileInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
//             Writer writer = new FileWriter(outputFile);
//             BufferedWriter bufferedWriter = new BufferedWriter(writer)
        ) {

            String headerRow = reader.readLine();
            if (headerRow == null || headerRow.isEmpty()) {
                return;
            }
            String[] headerValues = headerRow.split(" ");

            if (!InputFileConstants.CREATE.equalsIgnoreCase(headerValues[0])) {
                System.out.println("Not a valid input");
                throw new RuntimeException("Not Header input found");
            }
            int slots = Integer.parseInt(headerValues[1]);
            DataProcessor dataProcessor = new DataProcessorImpl(slots);
            System.out.println("Created parking of " + slots + " slots");
//            bufferedWriter.append("Created parking of ").append(String.valueOf(slots)).append(" slots");
//            bufferedWriter.append("\n");

            while (true) {
                String row = reader.readLine();
                if (row == null || row.isEmpty()) {
                    break;
                }
                if (row.equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.println(dataProcessor.process(row));
//                bufferedWriter.append(dataProcessor.process(row));
//                bufferedWriter.append("\n");
            }
//            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
