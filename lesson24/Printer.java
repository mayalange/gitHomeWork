import java.io.IOException;

@FunctionalInterface
public interface Printer {
    void writeIntoFile(String string) throws IOException;
}