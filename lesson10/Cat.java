public class Cat extends Animal {

    private static int catCount = 0;

    public Cat(String name) {
        super(name);
        catCount++;
    }

    @Override
    public void run(int length) {
        if (length <= 200) {
            System.out.println(getName() + " пробежал " + length + " м");
        } else {
            System.out.println("Котик не может пробежать больше 200 м");
        }
    }

    @Override
    public void swim(int length) {
        System.out.println("Котик не умеет плавать");
    }

    public static int getCatCount() {
        return catCount;
    }
}
