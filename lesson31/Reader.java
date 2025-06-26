public class Reader {
    private int readerId;
    private String name;
    private String email;
    private String phone;

    public Reader(int readerId, String name, String email, String phone) {
        this.readerId = readerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Reader(){};

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
