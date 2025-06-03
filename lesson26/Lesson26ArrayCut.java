import java.util.ArrayList;
import java.util.Arrays;

public class Lesson26ArrayCut {
    public static ArrayList<Integer> method1(int[] array) {
        if(Arrays.stream(array).filter(four -> four == 4).toArray().length > 0) {
            ArrayList<Integer> newArray = new ArrayList<>();
            int index = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] == 4) {
                    index = i;
                }
            }
            for (int i = index+1; i < array.length; i++) {
                newArray.add(array[i]);
            }
            System.out.println(index);
            return newArray;
        } else {
            throw new RuntimeException("В массиве не найдены четвёрки!");
        }
    }

    public static boolean method2(int[] array) {
        if(Arrays.stream(array).filter(four -> four == 4).toArray().length > 0 &&
                Arrays.stream(array).filter(four -> four == 1).toArray().length > 0) {
            for (int j : array) {
                if (j != 4 && j != 1) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}