import lombok.Getter;

@Getter
public enum Sorting {
    ASC("asc", "по возрастанию"),
    DESC("desc", "по убыванию");

    private final String code;
    private final String name;

    Sorting(String code, String name) {
        this.code = code;
        this.name = name;
    }
}