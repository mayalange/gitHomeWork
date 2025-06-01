package Assertions;

import java.util.Arrays;

public class Assertions {

    public static <T> void contains(T[] current, T[] toContain) {

        boolean isContains = false;
        for (int i = 0; i <= current.length - toContain.length; i++) {
            if (isSubarrayAt(current, toContain, i)) {
                isContains = true;
                break;
            }
        }

        if (!isContains) {
            throw new AssertException(new AssertResult<>(Arrays.toString(current), Arrays.toString(toContain), isContains));
        } else {
            throw new AssertSuccess(new AssertResult<>(Arrays.toString(current), Arrays.toString(toContain), isContains));
        }
    }

    private static <T> boolean isSubarrayAt(T[] array, T[] subarray, int startIndex) {
        for (int i = 0; i < subarray.length; i++) {
            if (!array[startIndex + i].equals(subarray[i])) {
                return false;
            }
        }
        return true;
    }
}