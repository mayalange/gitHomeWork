package phonebook;

import java.util.ArrayList;
import java.util.List;

public class TelephonBook {

    private final List<Record> phonebook = new ArrayList<>();

    public TelephonBook() {

    }

    public void add(Record record) {
        phonebook.add(record);
    }

    public Record find(String name) {
        for (Record record : phonebook) {
            if (record.getName().equals(name)) {
                return record;
            }
        }
        return null;
    }

    public List<Record> findAll(String name) {
        List<Record> records = new ArrayList<>();
        for (Record record : phonebook) {
            if (record.getName().equals(name)) {
                records.add(record);
            }
        }
        return records;
    }
}

