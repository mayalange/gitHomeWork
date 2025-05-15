import java.io.IOException;
import java.util.List;

public class MainForLesson20 {
    public static void main(String[] args) throws IOException {
        FileStorage fileStorage = new FileStorage();

        fileStorage.put("task", "task.txt", "Просто какой-то текст");

        String content = (String) fileStorage.get("task", "task.txt");

        System.out.println(content);

        String path = "C:\\Users\\марина\\IdeaProjects\\gitHomeWork\\fileStorage\\";

        FileStorageReader reader = new FileStorageReader(path + "\\");

        byte[] data = reader.read("task", "task.txt");
        System.out.println("\nСодержимое файла:");
        System.out.println(new String(data, "UTF-8"));

        List<byte[]> chunks = reader.read("task", "task.txt", 2048);
        System.out.println("\nКоличество chunks: " + chunks.size());
    }
}