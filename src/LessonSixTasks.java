package src;

import java.util.Arrays;
import java.util.Random;

public class LessonSixTasks {

    public static void main(String[] args) {
        int[] array = fillArray();
        System.out.println(Arrays.toString(array));
        sortShaker(array);

        int[] sourceArray = {1, 2, 3, 4};
        int[] targetArray = {5, 6, 7};
        arrayCopy(sourceArray, targetArray);

    }

    /**
     * Task #1
     */
    public static int[] arrayCopy(int[] sourceArray, int[] targetArray) {

        int copyLength = sourceArray.length + targetArray.length;

        int[] resultArray = new int[copyLength];

        if (targetArray.length == 0) { // []
            for (int i = 0; i < copyLength; i++) {
                resultArray[i] = sourceArray[i];
            }
        } else {
            for (int i = 0; i < targetArray.length; i++) { //
                resultArray[i] = targetArray[i];
            }

            for (int i = 0; i < sourceArray.length; i++) {
                resultArray[targetArray.length + i] = sourceArray[i];
            }
        }
        System.out.println(Arrays.toString(resultArray));
        return resultArray;
    }

    /**
     * Task #2
     */
    public static int[] fillArray() {
        Random random = new Random();

        int[] array = new int[10];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(0, 100);
        }
        return array;
    }

    public static int[] sortShaker(int[] array) {
        for (int i = 0; i < array.length; i++) {
            boolean isSorted = false;
            for (int k = 0; k < array.length - 1; k++) {
                if (array[k] > array[k + 1]) {
                    int temp = array[k];
                    array[k] = array[k + 1];
                    array[k + 1] = temp;
                    isSorted = true;
                }
            }
            for (int m = array.length - 2; m > 0; m--) {
                if (array[m] > array[m + 1]) {
                    int temp = array[m];
                    array[m] = array[m + 1];
                    array[m + 1] = temp;
                    isSorted = true;
                }
            }
            if (!isSorted) {
                break;
            }
        }
        System.out.println(Arrays.toString(array));
        return array;
    }
}