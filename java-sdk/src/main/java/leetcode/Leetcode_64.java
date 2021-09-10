package leetcode;

/**
 * @author chenfh   题目描述：矩阵的最小路径和，从左上角到右下角
 * @date 2021-06-07 10:25
 */
public class Leetcode_64 {
    public static void main(String[] args) {
        int[][] source = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum(source));
    }

    public static int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid[0].length;
        int m = grid.length;
        //大小取决于列数   每个元素记录当前列所需的权重，从行循环。
        int[] dp = new int[n];

        //行数
        for (int i = 0; i < m; i++) {
            //列数
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    //当列为0时，说明只能向下走，所以全重=当前全重+grid[i][0]。
                    dp[j] = dp[j];
                } else if (i == 0) {
                    //当行为0时，说明只能向右走，所以全重=前一个结点的+grid[0][j]
                    dp[j] = dp[j - 1];
                } else {
                    //此时，当前结点可从上往下或者从左往右，所以需要判断其中最小值。
                    dp[j] = Math.min(dp[j], dp[j - 1]);
                }
                dp[j] += grid[i][j];
            }
        }
        return dp[n - 1];
    }
}
