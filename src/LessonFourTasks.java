package src;

import java.util.Arrays;
import java.util.Random;

public class LessonFourTasks {

    public static void main(String[] args) {

        int[] arrayForFirstTask = {0, 1, 1, 0, 1};
        changeArray(arrayForFirstTask);

        fillArray();

        int[] arrayForThirdTask = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        multipleArray(arrayForThirdTask);

        int[][] arrayForFourthTasks = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};

        changeTwoDimensionalArray(arrayForFourthTasks);

        fillArrayWithParams(10, 5);

        int[] arrayForSixthTask = {1, 7, 10, 5, -1};
        maxAndMinInArray(arrayForSixthTask);
    }

    //Task #1
    public static void changeArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = 1;
            } else {
                array[i] = 0;
            }
        }
        System.out.println(Arrays.toString(array));
    }

    //Task #2
    public static void fillArray() {
        Random random = new Random();

        int[] array = new int[100];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(0, 100);
        }
        System.out.println(Arrays.toString(array));
    }

    //Task #3
    public static int[] multipleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] = array[i] * 6;
            }
        }
        System.out.println(Arrays.toString(array));
        return array;
    }

    //Task #4
    public static void changeTwoDimensionalArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            array[i][i] = 1; //главная диагональ
            for (int j = 0; j < array[i].length; j++) {
                array[i][array.length - 1 - i] = 1; // Дополнительная диагональ
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Task #5
    public static int[] fillArrayWithParams(int len, int initialValue) {
        Random random = new Random();

        int[] array = new int[len];
        for (int i = 0; i < array.length; i++) {
            array[i] = initialValue;
        }
        System.out.println(Arrays.toString(array));
        return array;
    }

    //Task #6
    public static void maxAndMinInArray(int[] array) {
        int max = array[0];
        int min = array[0];

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            } else if (array[i] < min) {
                min = array[i];
            }
        }
        System.out.println("max: " + max + ", min: " + min);
    }
}