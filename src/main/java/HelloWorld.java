import readerFiles.FileManager;
import readerFiles.JobFiles;

public class HelloWorld {
    public static void main(String[] args) {
        FileManager jobFiles = new JobFiles("folder");
        jobFiles.sortingAndWritingToANewFile();
    }
}
