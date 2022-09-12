import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        String rootFolder = "src/main/resources";
        runFindAllTxtFiles(rootFolder);
    }

    public static void runFindAllTxtFiles(String rootDirectory) throws IOException {
        Path rootPath = new File(rootDirectory).toPath();
        Files.walkFileTree(rootPath, new CustomFileVizitor());
    }
}
