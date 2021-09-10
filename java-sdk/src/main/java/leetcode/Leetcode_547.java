package leetcode;

/**
 * @author chenfh
 * 题目描述：
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a
 * 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 * @date 2021-06-07 10:25
 */
public class Leetcode_547 {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1},
        };
        System.out.println(count0(grid));
    }

    public static Integer count0(int[][] grid) {
        if (grid.length <= 0 || grid[0].length <= 0) {
            return 0;
        }
        int n = grid.length;
        boolean[] visited = new boolean[n];
        int num = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(grid,i,visited);
                num++;
            }
        }
        return num;
}

    public static void dfs(int[][] grid, int i, boolean[] visited) {
        visited[i] = true;
        for (int j = 0; j < grid.length; j++) {
            if (grid[i][j] == 1 && !visited[j]) {
                dfs(grid,j,visited);
            }
        }
    }
}
