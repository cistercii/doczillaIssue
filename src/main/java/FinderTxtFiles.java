import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FinderTxtFiles {

    public void run() {
        String separator = System.getProperty("file.separator");
        String rootFolder = "src" + separator + "main";
        String rootResourceFolder = rootFolder + separator + "resources";
        try {
            runFindAllTxtFiles(rootResourceFolder);
        } catch (IOException e) {
            System.err.println("Нет каталога Resource");
        }
        fillAllTextInFile(rootFolder + separator + "result.txt");
    }


    public static void runFindAllTxtFiles(String rootDirectory) throws IOException {
        Path rootPath = new File(rootDirectory).toPath();
        Files.walkFileTree(rootPath, new CustomFileVizitor());
    }

    public static void fillAllTextInFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName, StandardCharsets.UTF_8)) {
            for (Path path : CustomFileVizitor.getPathSet()) {
                try (Reader reader = new FileReader(path.toFile(), StandardCharsets.UTF_8)) {
                    reader.transferTo(writer);
                    writer.write("\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
