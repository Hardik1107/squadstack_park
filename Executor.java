package squadstack;

public class Executor {

    public static void main(String[] args) {
        //file input
        FileProcessor fileProcessor = new FileProcessorImpl();
        fileProcessor.process("/Users/arpitjain/repo/squad_stack/src/main/java/squadstack/input.txt");
    }
}