package lesson8;

import java.util.Random;
import java.util.Scanner;

public class StringHomeWork {

    public static void main(String[] args) {
        String string = "aabbcc";
        findSymbolOccurance(string, 'a');
        String source = "Apple";
        String target = "Plant";
        findWordPosition(source, target);
        System.out.println(stringReverse(target));
        String palindrom = "АБОБА";
        System.out.println(isPalindrome(palindrom));

        String[] array = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
                "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
                "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

        startGame(array);
    }

    /**
     * Task #1
     */
    public static void findSymbolOccurance(String str, char ch) {
        int size = 0;
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == ch) {
                size++;
            }
        }
        System.out.println(size);
    }

    /**
     * Task #2
     */
    public static void findWordPosition(String source, String target) {
        System.out.println(source.indexOf(target));
    }

    /**
     * Task #3
     */
    public static String stringReverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * Task #4
     */
    public static Boolean isPalindrome(String str) {
        String sb = new StringBuilder(str).reverse().toString();

        if (sb.equalsIgnoreCase(str)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Task #5
     */
    public static void startGame(String[] array) {
        Random random = new Random();
        int index = random.nextInt(array.length);
        System.out.println(array[index]);
        StringBuilder clue = new StringBuilder("###############");
        guessWord(array, index, clue);
    }

    public static void guessWord(String[] array, int index, StringBuilder clue) {
        System.out.println("Guess word: ");
        Scanner sc = new Scanner(System.in);
        String userAnswer = sc.nextLine();
        if (array[index].equals(userAnswer)) {
            System.out.println("Hurray, you guessed it!");
        } else {
            char[] userArray = userAnswer.toCharArray();
            printClue(clue, userArray, array, index);
            guessWord(array, index, clue);
        }
        sc.close();
    }

    public static void printClue(StringBuilder answerLine, char[] answerArray, String[] array, int index) {
        for (char ch : answerArray) {
            int charIndex = array[index].indexOf(ch);
            while (charIndex != -1) {
                answerLine.replace(charIndex, charIndex + 1, String.valueOf(ch));
                charIndex = array[index].indexOf(ch, charIndex + 1);
            }
        }
        if (answerLine.toString().contains(array[index])) {
            System.out.println("Hurray, you guessed all letters, the word is: " + array[index]);
            System.exit(1);
        }

        System.out.println(answerLine.toString());
    }
}
