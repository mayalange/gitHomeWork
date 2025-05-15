import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileStorage implements ObjectStorage {
    private final String storageDir = "fileStorage";

    @Override
    public void put(String namespace, String name, Object object) throws IOException {
        Path dirPath = Paths.get(storageDir, namespace);
        Files.createDirectories(dirPath);

        Path filePath = dirPath.resolve(name);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                Files.newOutputStream(filePath))) {
            outputStream.writeObject(object);
        }
    }

    @Override
    public Object get(String namespace, String name) throws IOException {
        Path filePath = Paths.get(storageDir, namespace, name);

        if (!Files.exists(filePath)) {
            return null;
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(
                Files.newInputStream(filePath))) {
            return inputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("not found", e);
        }
    }
}