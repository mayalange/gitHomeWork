public class ValueCalculator {
    float[] array = new float[1_000_000_00];
    float[] firstArray = new float[array.length / 2];
    float[] secondArray = new float[array.length / 2];

    public void setFirstArray(float value, int position) {
        this.firstArray[position] = value;
    }

    public void setSecondArray(float value, int position) {
        this.secondArray[position] = value;
    }

    public void doCalc() throws InterruptedException {
        var start = System.currentTimeMillis();

        for (int i = 0; i < array.length; i++) {
            array[i] = 1;
        }

        System.arraycopy(array, 0, firstArray, 0, array.length / 2);
        System.arraycopy(array, array.length / 2, secondArray, 0, array.length / 2);

        Thread threadFirst = new Thread(() -> {
            for (int i = 0; i < firstArray.length; i++) {
                setFirstArray(ValueCalculator.calculateValue(firstArray[i], i), i);
            }
        });

        Thread threadSecond = new Thread(() -> {
            for (int i = 0; i < secondArray.length; i++) {
                setSecondArray(ValueCalculator.calculateValue(secondArray[i], i), i);
            }
        });

        threadFirst.start();
        threadSecond.start();

        threadFirst.join();
        threadSecond.join();

        finalArray(firstArray, secondArray);

        System.out.println("Затраченное время = " + (System.currentTimeMillis() - start) + " мс");
    }

    public static float calculateValue(float value, float i) {
        return value * (float) Math.sin(0.2f + i / 5.0)
                * (float) Math.cos(0.2f + i / 5.0)
                * (float) Math.cos(0.4f + i / 2.0);
    }

    public void finalArray(float[] firstArray, float[] secondArray) {
        System.arraycopy(firstArray, 0, array, 0, firstArray.length);
        System.arraycopy(secondArray, 0, array, firstArray.length, secondArray.length);

    }
}