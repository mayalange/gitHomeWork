public class ArrayValueCalculator extends Exception {
    public static void main(String[] args) throws ArraySizeException, ArrayDataException{

        String[][] array = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "ы", "11", "12"},
                {"13", "14", "15", "16"}};

        doCalc(array);
    }

    public static void doCalc(String[][] array) throws ArraySizeException, ArrayDataException {
        if (array.length != 4) {
            throw new ArraySizeException("введен неверный размер массива");

        } else {
            for (int i = 0; i < array.length; i++) {
                if (array[i].length != 4) {
                    throw new ArraySizeException("введен неверный размер массива");
                }
            }
        }

        int sum = 0;
        int column = 0;
        int row = 0;
        try{
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    column = i + 1;
                    row = j + 1;
                    sum += Integer.parseInt(array[i][j]);
                }
            }
        } catch (Exception e) {
            throw new ArrayDataException("неправильное значение в ячейке: " + "\nСтрока = " + column +
                    "\nСтолбец = " + row);
        } finally {
            System.out.println("Сумма: " + sum);
        }
    }

    public static class ArraySizeException extends Exception {
        public ArraySizeException(String message) {
            super(message);
        }
    }

    public static class ArrayDataException extends Exception {
        public ArrayDataException (String message) {
            super(message);
        }
    }
}