import com.github.javafaker.Faker;

import java.util.Locale;

public class Lesson15Main {
    public static void main(String[] args) {

        Faker faker = new Faker(new Locale("ru"));

        CoffeeOrderBoard coffeeOrderBoard = new CoffeeOrderBoard();

        coffeeOrderBoard.add(faker.name().firstName());
        coffeeOrderBoard.add(faker.name().firstName());
        coffeeOrderBoard.add(faker.name().firstName());
        coffeeOrderBoard.add(faker.name().firstName());

        System.out.println(coffeeOrderBoard);

        System.out.println("----------------------------");

        System.out.println(coffeeOrderBoard.deliver());

        System.out.println("----------------------------");

        System.out.println(coffeeOrderBoard);

        System.out.println("----------------------------");

        System.out.println(coffeeOrderBoard.deliver(3));

        System.out.println("----------------------------");

        System.out.println(coffeeOrderBoard);

        System.out.println("----------------------------");

        coffeeOrderBoard.draw();
    }
}
