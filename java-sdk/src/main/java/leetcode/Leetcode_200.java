package leetcode;

/**
 * @author chenfh
 * 题目描述：
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * @date 2021-06-07 10:25
 */
public class Leetcode_200 {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1}
        };
        System.out.println(count0(grid));
    }

    public static Integer count0(int[][] grid) {
        if (grid.length <= 0 || grid[0].length <= 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        int num = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j);
                    num++;
                }
            }
        }

        return num;
    }

    public static void dfs(int[][] grid, int r, int c) {
        if (r >= grid.length || r < 0 || c >= grid[0].length || c < 0 || grid[r][c] == 0) {
            return;
        }
        int[][] direction = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        grid[r][c] = 0;
        for (int[] item : direction) {
            dfs(grid, r + item[0], c + item[1]);
        }
    }
}
