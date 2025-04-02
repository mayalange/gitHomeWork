package src;

import com.github.javafaker.Faker;
import lesson7.Car;
import lesson9.Employee;

import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();
        PrintStream out = System.out;

        Employee employee1 = new Employee(faker.name().fullName(), faker.job().title(), faker.internet().emailAddress(), faker.phoneNumber().cellPhone(),
                faker.number().numberBetween(100, 10000), faker.number().numberBetween(18, 65));

        Employee employee2 = new Employee(faker.name().fullName(), faker.job().title(), faker.internet().emailAddress(), faker.phoneNumber().cellPhone(),
                faker.number().numberBetween(100, 10000), faker.number().numberBetween(18, 65));

        Employee employee3 = new Employee(faker.name().fullName(), faker.job().title(), faker.internet().emailAddress(), faker.phoneNumber().cellPhone(),
                faker.number().numberBetween(100, 10000), faker.number().numberBetween(18, 65));

        Employee employee4 = new Employee(faker.name().fullName(), faker.job().title(), faker.internet().emailAddress(), faker.phoneNumber().cellPhone(),
                faker.number().numberBetween(100, 10000), faker.number().numberBetween(18, 65));

        Employee employee5 = new Employee(faker.name().fullName(), faker.job().title(), faker.internet().emailAddress(), faker.phoneNumber().cellPhone(),
                faker.number().numberBetween(100, 10000), faker.number().numberBetween(18, 65));

        Employee[] employees = {employee1, employee2, employee3, employee4, employee5};

        for (Employee employee : employees) {
            employee.printEmployeeInformation(out, employee);
        }

        Car car = new Car();
        car.start();
    }

    //Task #1
    private static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    //Task #2
    private static void checkSumSign() {
        int a = 9;
        int b = 1;

        int sum = a + b;

        if (sum >= 10) {
            System.out.println("The sum is positive");
        } else {
            System.out.println("The sum is negative");
        }
    }

    //Task #3
    private static void printColor() {
        int value = 90;

        if (value <= 0) {
            System.out.println("Red");
        } else if (value > 0 && value <= 100) {
            System.out.println("Yellow");
        } else {
            System.out.println("Green");
        }
    }

    //Task #4
    private static void compareNumbers() {
        int a = 9;
        int b = 10;

        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    //Task #5
    private static void compareNumbersWithLimits(int firstNumber, int secondNumber) {

        int sum = firstNumber + secondNumber;

        if (sum >= 10 && sum <= 20) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    //Task #6
    private static void checkNumber(int number) {

        if (number >= 0) {
            System.out.println("The number is positive");
        } else {
            System.out.println("The number is negative");
        }
    }

    //Task #7
    private static void checkNumberAndReturnBoolean(int number) {

        if (number >= 0) {
            System.out.println("false");
        } else {
            System.out.println("True");
        }
    }
}