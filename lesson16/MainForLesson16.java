import java.util.Arrays;
import java.util.Random;

public class MainForLesson16 {
    public static void main(String[] args) {
        Random rand = new Random();
        DfsVoyager voyager = new DfsVoyager();

        long[][] worldMap = new long[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                worldMap[i][j] = rand.nextLong(1,3);
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(Arrays.toString(worldMap[i]));
        }
        System.err.println(voyager.lookupIslands(worldMap));
    }
}