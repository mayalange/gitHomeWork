import lombok.Getter;

@Getter
public enum LoggingLevel {
    INFO("INFO", "режим инфо"),
    DEBUG("DEBUG", "режим дебаг");

    private final String code;
    private final String name;

    LoggingLevel(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + code + "]";
    }
}