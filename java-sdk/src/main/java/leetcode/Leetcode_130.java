package leetcode;

/**
 * @author chenfh
 * 题目描述：
 * 填充封闭区域，边界上的o不填。
 * <p>
 * For example,
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * <p>
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * @date 2021-06-07 10:25
 */
public class Leetcode_130 {
    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'x', 'x', 'x', 'x'},
                {'x', 'o', 'o', 'x'},
                {'x', 'x', 'o', 'x'},
                {'x', 'o', 'x', 'x'}

        };
        System.out.println(count0(grid));
    }

    /**
     * 先填充边界o->t
     * 再填内部o->x
     * 最后把t->o
     */
    public static Integer count0(char[][] grid) {
        if (grid.length <= 0 || grid[0].length <= 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, m - 1);
        }

        for (int i = 0; i < m; i++) {
            dfs(grid, 0, i);
            dfs(grid, n - 1, i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 't') {
                    grid[i][j] = 'o';
                } else if (grid[i][j] == 'o') {
                    grid[i][j] = 'x';
                }

                System.out.print(grid[i][j]);
            }
            System.out.println("\n");
        }

        return 0;
    }

    public static void dfs(char[][] grid, int r, int c) {
        if (r >= grid.length || r < 0 || c >= grid[0].length || c < 0 || grid[r][c] != 'o') {
            return;
        }
        int[][] direction = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        grid[r][c] = 't';
        for (int[] item : direction) {
            dfs(grid, r + item[0], c + item[1]);
        }
    }
}
