import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MyFileReader {
    public static List<String> readFile(String filePath) {
        try {
            Path path = Path.of(filePath);
            IO.print(path);
            List<String> lines = Files.readAllLines(path);
            return lines;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }
}