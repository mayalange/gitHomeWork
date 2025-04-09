public class Main {

    public static void main(String[] args) {
        Animal cat = new Cat("Барсик");
        Animal cat2 = new Cat("Юла");
        Animal dog = new Dog("Шарик");

        cat.run(50);
        cat2.run(90);
        dog.run(80);
        cat.swim(10);
        dog.swim(10);

        countAnimal();
    }

    public static void countAnimal() {
        System.out.println("Количество котов: " + Cat.getCatCount());
        System.out.println("Количество собак: " + Dog.getDogCount());
        System.out.println("Общее количество животных: " + Animal.getTotalCount());
    }
}