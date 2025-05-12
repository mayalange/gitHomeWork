import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileStorageReader implements ObjectStorageReader {
    private final String storagePath;

    public FileStorageReader(String storagePath) {
        this.storagePath = storagePath;
    }

    @Override
    public byte[] read(String namespace, String name) throws IOException {
        Path filePath = Paths.get(storagePath, namespace, name);
        if (!Files.exists(filePath)) {
            return null;
        }
        return Files.readAllBytes(filePath);
    }

    @Override
    public List<byte[]> read(String namespace, String name, int chunkSize) throws IOException {
        byte[] allBytes = read(namespace, name);
        if (allBytes == null) {
            return null;
        }

        List<byte[]> chunks = new ArrayList<>();
        for (int i = 0; i < allBytes.length; i += chunkSize) {
            int end = Math.min(allBytes.length, i + chunkSize);
            byte[] chunk = new byte[end - i];
            System.arraycopy(allBytes, i, chunk, 0, chunk.length);
            chunks.add(chunk);
        }
        return chunks;
    }
}