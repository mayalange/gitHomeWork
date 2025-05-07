import lombok.Getter;

@Getter
public enum Command {
    RED("red", "красная"),
    BLUE("blue", "синяя"),
    GREEN("green", "зелёная");

    private final String code;
    private final String name;

    Command(String code, String name) {
        this.code = code;
        this.name = name;
    }
}