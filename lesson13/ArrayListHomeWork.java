import com.github.javafaker.Faker;
import phonebook.Record;
import phonebook.TelephonBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ArrayListHomeWork {

    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("ru"));

        //Task #1
        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            arrayList.add(faker.name().firstName());
        }

        System.out.println(arrayList);

        countOccurance(arrayList, "Максим");

        //Task #2
        int[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        System.out.println(toList(intArray));

        //Task #3
        List<Integer> listWithDublicates = new ArrayList<>(List.of(1, 2, 2, 3, 3, 6, 7, 9, 9, 3));

        findUnique(listWithDublicates);

        //Task #4
        List<String> words = new ArrayList<>(List.of(
                "apple", "banana", "apple", "orange", "banana", "apple",
                "grape", "orange", "kiwi", "banana", "kiwi", "apple"
        ));

        System.out.println(calcOccurance(words));


        //Lesson#2
        String name = faker.name().firstName();
        Record record1 = new Record(name, faker.phoneNumber().cellPhone());
        Record record2 = new Record(faker.name().firstName(), faker.phoneNumber().cellPhone());
        Record record3 = new Record(faker.name().firstName(), faker.phoneNumber().cellPhone());
        Record record4 = new Record(faker.name().firstName(), faker.phoneNumber().cellPhone());
        Record record5 = new Record(name, faker.phoneNumber().cellPhone());
        Record record6 = new Record(name, faker.phoneNumber().cellPhone());

        TelephonBook telephonBook = new TelephonBook();
        telephonBook.add(record1);
        telephonBook.add(record2);
        telephonBook.add(record3);
        telephonBook.add(record4);
        telephonBook.add(record5);
        telephonBook.add(record6);

        System.out.println(telephonBook.find(name).toString());

        System.out.println(telephonBook.findAll(name).toString());
    }

    //Task #1
    public static void countOccurance(List<String> list, String line) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(line)) {
                count++;
            }
        }
        System.out.println("Слово " + line + " повторилось: " + count + " раз");
    }

    //Task #2
    public static List<Integer> toList(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    //Task #3
    public static void findUnique(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (Objects.equals(list.get(i), list.get(j))) {
                    list.remove(j);
                }
            }
        }
        System.out.println(list);
    }

    //Task #4
    public static List<String> calcOccurance(List<String> list) {
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int count = 1;
            for (int j = i + 1; j < list.size(); j++) {
                if (Objects.equals(list.get(i), list.get(j))) {
                    count++;
                    list.remove(j);
                }
            }
            resultList.add(list.get(i) + " " + count);
        }
        return resultList;
    }
}
