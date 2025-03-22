package src;

import java.util.Arrays;
import java.util.Random;

public class LessonFiveTasks {
    private static final int CAPACITY = 1;

    public static void main(String[] args) {
        int[] firstArray = initArray(10);
        int[] secondArray = addElementToArray(firstArray, 7);
        getElementByIndex(firstArray, 6);
        getArraySize(firstArray);
        getArraySize(secondArray);

        int[] firstQueue = initQueue(10);
        int[] secondQueue = addElementToQueue(firstQueue, 8);
        getElementFromQueue(secondQueue);
        secondQueue = decreaseQueueSize(secondQueue);
        getElementFromQueue(secondQueue);
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

    public static int[] addElementToArray(int[] array, int newElement) {
        int[] newArray = increaseArraySize(array);

        newArray[newArray.length - 1] = newElement;

        System.out.println(Arrays.toString(newArray));

        return newArray;
    }

    public static int[] increaseArraySize(int[] array) {
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

    /**
     * Task #2
     */
    public static int[] initQueue(int size) {
        Random random = new Random();
        int[] queue = new int[size];

        for (int i = 0; i < size; i++) {
            queue[i] = random.nextInt(10);
        }
        System.out.println(Arrays.toString(queue));

        return queue;
    }

    public static int[] addElementToQueue(int[] queue, int newElement) {
        int[] newQueue = increaseQueueSize(queue);

        newQueue[newQueue.length - 1] = newElement;

        System.out.println(Arrays.toString(newQueue));

        return newQueue;
    }

    public static int[] increaseQueueSize(int[] array) {
        int[] newQueue = new int[array.length + CAPACITY];
        newQueue = Arrays.copyOf(array, newQueue.length);

        return newQueue;
    }

    public static int getElementFromQueue(int[] queue) {
        int element = queue[0];
        decreaseQueueSize(queue);

        System.out.println(element);

        return element;
    }

    public static int[] decreaseQueueSize(int[] queue) {
        int[] newQueue = new int[queue.length - 1];
        System.arraycopy(queue, 1, newQueue, 0, queue.length - 1);

        return newQueue;
    }
}