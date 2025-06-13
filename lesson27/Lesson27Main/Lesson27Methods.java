public class Lesson27Methods {

    public static int sum(int firstNumber, int secondNumber) {
        return firstNumber + secondNumber;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int divide(int a, int b) {
        try {
            int i = a / b;
        } catch (Exception e) {
            throw new MatchException("Нельзя делить на ноль", e.getCause());
        }
        return a / b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static int square(int a) {
        return a * a;
    }

}
