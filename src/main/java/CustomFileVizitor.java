import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class CustomFileVizitor extends SimpleFileVisitor<Path> {
    static Set<Path> pathSet = new TreeSet<>(Comparator.comparing(Path::getFileName));

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        PathMatcher matcher =
                FileSystems.getDefault().getPathMatcher("glob:*.txt");

        if (matcher.matches(file.getFileName())) {
            pathSet.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
