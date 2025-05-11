import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class MainForLesson19 {
    public static void main(String[] args) throws IOException {
        File folder = new File("C:/Users/марина/IdeaProjects/gitHomeWork/lesson19/files/");
        File[] files = folder.listFiles();
        String fileName = "";
        if (files.length != 0) {
            fileName = List.of(String.valueOf(files[files.length - 1]).split("\\\\")).getLast();
        }
        if (!fileName.isEmpty()) {
            FileLoggerConfiguration fileLoggerConfiguration = new FileLoggerConfiguration(LoggingLevel.DEBUG, 200, fileName);

            System.out.println(fileLoggerConfiguration.getFileFormat());

            FileLogger fileLogger = new FileLogger(fileLoggerConfiguration);

            fileLogger.info("Ошибка 404");
            fileLogger.debug("Ошибка 500");


        } else {
            FileLoggerConfiguration fileLoggerConfiguration = new FileLoggerConfiguration(LoggingLevel.DEBUG, 200);

            System.out.println(fileLoggerConfiguration.getFileFormat());

            FileLogger fileLogger = new FileLogger(fileLoggerConfiguration);

            fileLogger.debug("Ошибка 500");
        }
    }
}
