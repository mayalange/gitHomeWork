import java.io.IOException;

public interface ObjectStorage<T> {

    void put(String namespace, String name, T object) throws IOException;

    T get(String namespace, String name) throws IOException;
}