public class AttandanceRecord {

    private String user_id;
    private String timestamp;

    public String getUser_id() {
        return user_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public AttandanceRecord(String user_id, String timestamp) {
        this.user_id = user_id;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "\"" + user_id + "\"," +  " " + '\"'  +
                timestamp + '\"';
    }
}
