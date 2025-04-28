import java.util.Arrays;

public class DfsVoyager implements Voyager {

    @Override
    public long lookupIslands(long[][] worldMap) {
        int islandCount = 0;
        for (int i = 0; i < worldMap.length; i++) {
            for (int j = 0; j < worldMap[i].length; j++) {
                if (worldMap[i][j] == 1) {
                    dfs(worldMap, i, j);
                    islandCount++;
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            System.err.println(Arrays.toString(worldMap[i]));
        }
        return islandCount;
    }

    private void dfs(long[][] worldMap, int x, int y) {
        if (x < 0 || x >= worldMap.length || y < 0 || y >= worldMap[0].length || worldMap[x][y] != 1) {
            return; // Выход за границы или уже посещённая клетка
        }
        worldMap[x][y] = 0;
        dfs(worldMap, x + 1, y);
        dfs(worldMap, x - 1, y);
        dfs(worldMap, x, y + 1);
        dfs(worldMap, x, y - 1);
    }
}