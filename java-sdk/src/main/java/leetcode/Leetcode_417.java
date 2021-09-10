package leetcode;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenfh
 * 题目描述：
 * 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆
 * 的右边界和下边界。规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
 * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
 * <p>
 * Given the following 5x5 matrix:
 * <p>
 * Pacific ~   ~   ~   ~   ~
 * ~  1   2   2   3  (5) *
 * ~  3   2   3  (4) (4) *
 * ~  2   4  (5)  3   1  *
 * ~ (6) (7)  1   4   5  *
 * ~ (5)  1   1   2   4  *
 * *   *   *   *   * Atlantic
 * <p>
 * Return:
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 * @date 2021-06-07 10:25
 */
public class Leetcode_417 {
    public static int[][] direction = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static int[][] grid = new int[][]{
            {1, 2, 2, 3, 5},
            {3, 2, 3, 4, 4},
            {2, 4, 5, 3, 1},
            {6, 7, 1, 4, 5},
            {5, 1, 1, 2, 4},
    };

    public static void main(String[] args) {
        System.out.println(count0(grid));
    }

    /**
     * 先查找从太平洋可到达的所有区域
     * 再查找从大西洋可到达的所有区域
     * 最后求交集
     */
    public static Integer count0(int[][] grid) {
        if (grid.length <= 0 || grid[0].length <= 0) {
            return 0;
        }
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        //y
        int y = grid.length;
        //x
        int x = grid[0].length;

        boolean[][] canReachT = new boolean[y][x];
        boolean[][] canReachX = new boolean[y][x];
        //太平洋 左+上   大西洋 右+下
        for (int i = 0; i < y; i++) {
            dfs(grid, i, 0, canReachT);
            dfs(grid, i, x - 1, canReachX);
        }

        for (int i = 0; i < x; i++) {
            dfs(grid, 0, i, canReachT);
            dfs(grid, y - 1, i, canReachX);
        }

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (canReachT[i][j] && canReachX[i][j]) {
                    result.add(new Pair<>(i, j));
                    System.out.print("[" + i + "][" + j + "],");
                }
            }
        }

        return 0;
    }

    public static void dfs(int[][] grid, int r, int c, boolean[][] canReach) {
        if (canReach[r][c]) {
            return;
        }
        canReach[r][c] = true;
        for (int[] item : direction) {
            int nextR = r + item[0];
            int nextC = c + item[1];
            if (nextR >= grid.length || nextR < 0 || nextC >= grid[0].length || nextC < 0
                    || grid[nextR][nextC] < grid[r][c]) {
                continue;
            }
            dfs(grid, nextR, nextC, canReach);
        }
    }
}
