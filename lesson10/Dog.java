public class Dog extends Animal {

    private static int dogCount = 0;

    public Dog(String name) {
        super(name);
        dogCount++;
    }

    @Override
    public void run(int length) {
        if (length <= 500) {
            System.out.println(getName() + " пробежал " + length + " м");
        } else {
            System.out.println("Собачка не может пробежать больше 500 м");
        }
    }

    @Override
    public void swim(int length) {
        if (length <= 10) {
            System.out.println(getName() + " проплыл " + length + " м");

        } else {
            System.out.println("Собачка не может проплыть больше 10 м");
        }
    }

    public static int getDogCount() {
        return dogCount;
    }
}