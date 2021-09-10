package leetcode;

/**
 * @author chenfh
 * 题目描述：
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格
 * 内的字母不允许被重复使用。
 * <p>
 * For example,
 * Given board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 * @date 2021-06-07 10:25
 */
public class Leetcode_79 {
    private final static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private final static char[][] grid = new char[][]{
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
    };
    private static int m = grid.length;
    private static int n = grid[0].length;

    public static void main(String[] args) {
        System.out.println(count0("ABCCED"));
    }

    public static boolean count0(String goal) {

        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (match(i, j, 0, goal, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean match(Integer r, Integer c, Integer curLength, String goal, boolean[][] visited) {
        if (curLength == goal.length()) {
            return true;
        }

        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] != goal.charAt(curLength) || visited[r][c]) {
            return false;
        }
        visited[r][c] = true;

        for (int[] item : direction) {
            int nextR = r + item[0];
            int nextC = c + item[1];

            if (match(nextR, nextC, curLength + 1, goal, visited)) {
                return true;
            }
        }

        visited[r][c] = false;
        return false;
    }
}
