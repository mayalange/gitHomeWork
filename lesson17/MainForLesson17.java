import java.util.Arrays;
import java.util.List;

public class MainForLesson17 {

    public static void main(String[] args) {

        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        System.out.println("До: " + Arrays.toString(array));
        changeElement(array);
        System.out.println("После: " + Arrays.toString(array));

        changeToList(array);

        Apple apple = new Apple();
        Orange orange = new Orange();
        Box box = new Box<>(Orange.class);

        System.out.println("Вес яблока: " + apple.getWeight());
        System.out.println("Вес апельсина: " + orange.getWeight());

        Box<Apple> appleBox = new Box<>(Apple.class);
        appleBox.add(new Apple());
        appleBox.add(new Apple());

        Box<Orange> orangeBox = new Box<>(Orange.class);
        orangeBox.add(new Orange());

        System.out.println(appleBox.compare(orangeBox));
        orangeBox.add(new Orange());
        System.out.println(appleBox.compare(orangeBox));

        Box<Apple> emptyBox1 = new Box<>(Apple.class);
        Box<Orange> emptyBox2 = new Box<>(Orange.class);
        System.out.println(emptyBox1.compare(emptyBox2));

        System.out.println(box.getWeight());

        appleBox.transfer(emptyBox1);
    }

    //Task #1
    public static <T> void changeElement(T[] array) {
        for (int i = 0; i < array.length - 1; i += 2) {
            T temp = array[i];
            array[i] = array[i + 1];
            array[i + 1] = temp;
        }
    }

    //Task #2
    public static <T> List<T> changeToList(T[] array) {
        return Arrays.asList(array);
    }
}