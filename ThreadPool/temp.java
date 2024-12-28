import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class temp
{
    public static void main(String[] args) {
        String filePath = "readerOperation";

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            lines.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("error");
        }
    }
}
