import java.io.IOException;
import java.util.List;

public interface ObjectStorageReader {

    byte[] read(String namespace, String name) throws IOException;

    List<byte[]> read(String namespace, String name, int chunkSize) throws IOException;
}