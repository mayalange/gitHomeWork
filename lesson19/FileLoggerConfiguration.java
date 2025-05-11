import lombok.Getter;
import lombok.Setter;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static java.time.format.DateTimeFormatter.ofPattern;

@Getter
@Setter
public class FileLoggerConfiguration implements LoggerConfiguration {
    private final String path = "C:/Users/марина/IdeaProjects/gitHomeWork/lesson19/files/";
    private String fileName = "Log_" + ZonedDateTime.now(ZoneId.of("Europe/Moscow")).format(ofPattern("dd.MM.yyyy-HH.mm.ss")) + ".log";
    private LoggingLevel logLevel;
    private int maxFileSize;
    private String message = "";
    private String fileFormat;

    @Override
    public LoggingLevel level() {
        return null;
    }

    @Override
    public String pattern() {
        return "";
    }

    public FileLoggerConfiguration(LoggingLevel logLevel, int maxFileSize) {
        this.logLevel = logLevel;
        this.maxFileSize = maxFileSize;
        this.fileFormat = ZonedDateTime.now(ZoneId.of("Europe/Moscow")).format(ofPattern("dd.MM.yyyy-HH:mm:ss"))
                + logLevel.toString() + " Сообщение: ";
    }

    public FileLoggerConfiguration(LoggingLevel logLevel, int maxFileSize, String fileName) {
        this.logLevel = logLevel;
        this.maxFileSize = maxFileSize;
        this.fileName = fileName;
    }

    public void setFileFormat(LoggingLevel level) {
        this.fileFormat = ZonedDateTime.now(ZoneId.of("Europe/Moscow")).format(ofPattern("dd.MM.yyyy-HH:mm:ss"))
                + logLevel.toString() + " Сообщение: ";
    }
}