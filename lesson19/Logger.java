import java.io.IOException;

public interface Logger {
    public void debug(String message) throws IOException;

    public void info(String message) throws IOException;
}
