package src;

import java.util.Arrays;
import java.util.Random;

public class LessonFiveTasks {
    private static final int CAPACITY = 1;

    public static void main(String[] args) {
        int[] firstArray = initArray(10);
        int[] secondArray = addElement(firstArray, 7);
        getElementByIndex(firstArray, 6);
        getArraySize(firstArray);
        getArraySize(secondArray);
    }

    /**
     * Task #1
     */
    public static int[] initArray(int size) {
        Random random = new Random();
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10);
        }
        System.out.println(Arrays.toString(array));

        return array;
    }

    public static int[] addElement(int[] array, int newElement) {
        int[] newArray = increaseSize(array);

        newArray[newArray.length - 1] = newElement;

        System.out.println(Arrays.toString(newArray));

        return newArray;
    }

    public static int[] increaseSize(int[] array) {
        int[] newArray = new int[array.length + CAPACITY];
        newArray = Arrays.copyOf(array, newArray.length);

        return newArray;
    }

    public static int getElementByIndex(int[] array, int index) {
        System.out.println(array[index]);
        return array[index];
    }

    public static int getArraySize(int[] array) {
        System.out.println("array size: " + array.length);
        return array.length;
    }


}
