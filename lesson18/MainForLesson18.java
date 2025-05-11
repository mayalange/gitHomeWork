import com.github.javafaker.Faker;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainForLesson18 {
    static Random random = new Random();

    public static void main(String[] args) {

        //Task #1
        IntStream randomNumbers = random.ints(100, 1, 1001);
        randomNumbers
                .sorted().limit(10).distinct()
                .boxed()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        //Task #2
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Anna");
        Predicate<String> startsWithA = name -> name.startsWith("A");

        List<String> filteredNames = filterCollection(names, startsWithA);
        System.out.println(filteredNames);

        //Task #3
        Collection<String> words = List.of("hello", ",", "world", "!", "java", "stream");

        String result = filterAndJoin(words, s -> s.length() > 1);

        System.out.println(result);

        //Task #4
        System.out.println(filterNumbers(List.of(4, 2, 5, 1, 3), Sorting.ASC));
        System.out.println(filterNumbers(List.of(1, 2, 3, 4, 5), Sorting.DESC));

        //Task #5
        System.out.println(factorial(5));


        //Task #6
        Faker faker = new Faker(new Locale("ru"));
        Camp camp = new Camp();
        Boyscout boyscout1 = new Boyscout(faker.name().firstName(), faker.number().numberBetween(18, 40), Command.BLUE);
        Boyscout boyscout2 = new Boyscout(faker.name().firstName(), faker.number().numberBetween(18, 40), Command.GREEN);
        Boyscout boyscout3 = new Boyscout(faker.name().firstName(), faker.number().numberBetween(18, 40), Command.RED);
        Boyscout boyscout4 = new Boyscout(faker.name().firstName(), faker.number().numberBetween(18, 40), Command.BLUE);
        camp.addBoyscout(boyscout1);
        camp.addBoyscout(boyscout2);
        camp.addBoyscout(boyscout3);
        camp.addBoyscout(boyscout4);
        camp.split();
    }

        //Task #2
        public static <T> List<T> filterCollection(Collection<T> collection, Predicate<T> predicate) {
            return collection.stream()
                    .filter(predicate)
                    .collect(Collectors.toList());
        }

        //Task #3
        public static String filterAndJoin(Collection<String> strings, Predicate<String> predicate) {
            return strings.stream()
                    .filter(predicate)
                    .collect(Collectors.joining("|"));
        }

        //Task #4
        public static List<Integer> filterNumbers(Collection<Integer> numbers, Sorting sorting) {
            if (sorting.equals(Sorting.ASC)) {
                return  numbers.stream().distinct().sorted().toList();
            } else {
                return  numbers.stream().distinct().sorted(Comparator.reverseOrder()).toList();
            }
        }

        //Task #5
        public static int factorial(int n) {
            return IntStream.rangeClosed(1, n)
                    .reduce(1, (a, b) -> a * b);
        }
}