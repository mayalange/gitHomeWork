import java.util.Arrays;
import java.util.List;

public class Lesson14Main {
    public static void main(String[] args) {

        List<AttandanceRecord> records = Arrays.asList(
                new AttandanceRecord("user1", "09:15"),
                new AttandanceRecord("user2", "10:00"),
                new AttandanceRecord("user1", "09:45"),
                new AttandanceRecord("user3", "11:30"),
                new AttandanceRecord("user2", "10:20"),
                new AttandanceRecord("user4", "14:10"),
                new AttandanceRecord("user1", "09:55"),
                new AttandanceRecord("user2", "10:45"),
                new AttandanceRecord("user4", "14:25"),
                new AttandanceRecord("user3", "11:50"),
                new AttandanceRecord("user5", "15:00"),
                new AttandanceRecord("user1", "16:30"),
                new AttandanceRecord("user3", "11:59")
        );
        AttandanceLogger attandanceLogger = new AttandanceLogger(records);

        //Task #1
        attandanceLogger.addAttandanceRecords(new AttandanceRecord("user22", "15:30"));

        System.out.println(attandanceLogger);

        //Task #2
        System.out.println(attandanceLogger.loggerUserCount(attandanceLogger.getAttandanceRecords()));

        //Task #3
        attandanceLogger.findMostPopularTime(records);
    }
}