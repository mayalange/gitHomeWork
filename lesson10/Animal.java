public abstract class Animal {

    private static int totalCount = 0;

    private String name;

    public String getName() {
        return name;
    }

    public Animal(String name) {
        this.name = name;
        totalCount++;
    }

    public abstract void run(int length);

    public abstract void swim(int length);

    public static int getTotalCount() {
        return totalCount;
    }
}
