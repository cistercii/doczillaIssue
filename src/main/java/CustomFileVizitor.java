import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

public class CustomFileVizitor extends SimpleFileVisitor<Path> {
    public static Collection<Path> pathSet = new TreeSet<>(Comparator.comparing(Path::getFileName));

    public static Collection<Path> getPathSet() {
        return pathSet;
    }

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
