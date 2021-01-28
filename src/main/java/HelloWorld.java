import readerFiles.JobFiles;

public class HelloWorld {
    public static void main(String[] args) {
        JobFiles jobFiles = new JobFiles("folder");
        jobFiles.sortingAndWritingToANewFile();
    }
}
