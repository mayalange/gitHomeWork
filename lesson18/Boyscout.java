import lombok.Data;

@Data
public class Boyscout {
    private String name;
    private int age;
    private Command command;

    public Boyscout(String name, int age, Command command) {
        this.name = name;
        this.age = age;
        this.command = command;
    }

    @Override
    public String toString() {
        return "{name='" + name + '\'' +
                ", age=" + age + '}';
    }
}