import java.util.Random;

public class MainForLesson22 {

    public static void main(String[] args) throws InterruptedException{

        //Task #1
        ThreadSafeList.action();

        //Task #2
        Random random = new Random();

        PetrolStation petrolStation = new PetrolStation(100f);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    petrolStation.doTank(random.nextFloat(10, 40));
                } catch (Exception e){
                    e.printStackTrace();
                }
            }).start();
        }
    }
}