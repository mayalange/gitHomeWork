import java.util.*;
import java.util.stream.Collectors;

public class AttandanceLogger {

    private ArrayList<AttandanceRecord> attandanceRecords = new ArrayList<>();

    public AttandanceLogger(List<AttandanceRecord> attandanceRecords) {
        this.attandanceRecords.addAll(attandanceRecords);
    }

    public ArrayList<AttandanceRecord> getAttandanceRecords() {
        return attandanceRecords;
    }

    public ArrayList<AttandanceRecord> addAttandanceRecords(AttandanceRecord attandanceRecord) {
        attandanceRecords.add(attandanceRecord);
        return attandanceRecords;
    }

    public Map<String, Integer> loggerUserCount(List<AttandanceRecord> attandanceRecords) {
        Map<String, Integer> userCount = new TreeMap<>(Comparator.comparing(String::valueOf));
        for (AttandanceRecord attandanceRecord : attandanceRecords) {
            userCount.merge(attandanceRecord.getUser_id(), 1, Integer::sum);
        }
        return userCount;
    }

    @Override
    public String toString() {
        return
                attandanceRecords.stream()
                        .map(AttandanceRecord::toString)
                        .collect(Collectors.joining("\n"));
    }

    public String findMostPopularTime(List<AttandanceRecord> attandanceRecords) {
        String mostPopularTime = "";
        int count = 0;
        Map<String, Integer> hourCount = new TreeMap<>();
        for (AttandanceRecord attandanceRecord : attandanceRecords) {
            String hour = attandanceRecord.getTimestamp().substring(0, 2) + ":00";
            hourCount.put(hour, hourCount.getOrDefault(hour, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : hourCount.entrySet()) {
            if (entry.getValue() > count) {
                mostPopularTime = entry.getKey();
                count = entry.getValue();
            }
        }
        List<String> mostPopularTimeList = new ArrayList<>();
        for (AttandanceRecord attandanceRecord : attandanceRecords) {
            String hour = attandanceRecord.getTimestamp().substring(0, 2);
            if (mostPopularTime.substring(0,2).equals(hour)) {
                mostPopularTimeList.add(attandanceRecord.getTimestamp());
            }
        }
        System.out.println("Most popular time: " + mostPopularTime + " (" + count + " посещения: " + mostPopularTimeList + ")");

        return "";
    }
}