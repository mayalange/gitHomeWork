import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class FilePrinter implements Printer {
    private static final Path path = Path.of("C:", "Users", "марина", "IdeaProjects",
            "gitHomeWork", "lesson24", "files", "test.txt");

    @Override
    public void writeIntoFile(String string) {
        try (FileWriter fileWriter = new FileWriter(String.valueOf(path), true)){
            fileWriter.write(string + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл, повторите попытку позже");
        }
    }
}