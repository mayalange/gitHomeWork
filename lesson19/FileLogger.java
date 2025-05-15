import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static java.time.format.DateTimeFormatter.ofPattern;

public class FileLogger implements Logger {
    private FileLoggerConfiguration fileLoggerConfiguration;

    public FileLogger(FileLoggerConfiguration fileLoggerConfiguration) {
        this.fileLoggerConfiguration = fileLoggerConfiguration;
    }

    @Override
    public void debug(String message) throws IOException {
        fileLoggerConfiguration.setLogLevel(LoggingLevel.DEBUG);
        fileLoggerConfiguration.setFileFormat(fileLoggerConfiguration.getLogLevel());
        writeToFile(message);
    }

    @Override
    public void info(String message) throws IOException {
        fileLoggerConfiguration.setLogLevel(LoggingLevel.INFO);
        fileLoggerConfiguration.setFileFormat(fileLoggerConfiguration.getLogLevel());
        writeToFile(message);
    }

    public void writeToFile(String message) throws IOException {
        fileLoggerConfiguration.setMessage(fileLoggerConfiguration.getFileFormat() + message + "\n");
        var file = new File(fileLoggerConfiguration.getPath() + fileLoggerConfiguration.getFileName());
        boolean isNewFile = !file.exists();

        if (isNewFile || fileLoggerConfiguration.getMaxFileSize() > file.length() + fileLoggerConfiguration.getMessage().getBytes().length + 1) {
            try (BufferedWriter writer = new BufferedWriter(
                    new FileWriter(fileLoggerConfiguration.getPath() + fileLoggerConfiguration.getFileName(), true))) {
                writer.write(fileLoggerConfiguration.getMessage());
            }
        } else {

            fileLoggerConfiguration.setFileName("Log_" + ZonedDateTime.now(ZoneId.of("Europe/Moscow")).format(ofPattern("dd.MM.yyyy-HH.mm.ss")) + ".log");
            try (BufferedWriter writer = new BufferedWriter(
                    new FileWriter(fileLoggerConfiguration.getPath() + fileLoggerConfiguration.getFileName(), true))) {
                writer.write(fileLoggerConfiguration.getMessage());
            }
        }
    }
}