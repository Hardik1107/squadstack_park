package squadstack;

public class Executor {

    public static void main(String[] args) {
        //file input
        FileProcessor fileProcessor = new FileProcessorImpl();
        fileProcessor.process(args[0]);
    }
}