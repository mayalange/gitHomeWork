import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Map<String, Integer> userCount = new HashMap<>();
        attandanceRecords.sort((e1, e2) -> e2.getTimestamp().compareTo(e1.getTimestamp()));
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
}

