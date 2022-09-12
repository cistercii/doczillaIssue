import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FinderTxtFiles {

    public void run() throws IOException {
        String separator = System.getProperty("file.separator");
        String rootFolder = "src" + separator + "main";
        String rootResourceFolder = rootFolder + separator + "resources";
        runFindAllTxtFiles(rootResourceFolder);
        fillAllTextInFile(rootFolder + separator + "result.txt");
    }


    public static void runFindAllTxtFiles(String rootDirectory) throws IOException {
        Path rootPath = new File(rootDirectory).toPath();
        Files.walkFileTree(rootPath, new CustomFileVizitor());
    }

    public static void fillAllTextInFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Path path : CustomFileVizitor.getPathSet()) {
                List<String> list = Files.readAllLines(path);
                for (String str : list) {
                    writer.write(str + "\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
