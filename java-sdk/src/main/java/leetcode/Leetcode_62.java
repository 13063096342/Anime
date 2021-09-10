package leetcode;

import java.util.Arrays;

/**
 * @author chenfh   题目描述：统计从矩阵左上角到右下角的路径总数，每次只能向右或者向下移动。
 * @date 2021-06-07 10:25
 */
public class Leetcode_62 {
    public static void main(String[] args) {
        System.out.println(minPathSum(3, 7));
    }

    public static int minPathSum(int n, int m) {
        //大小取决于列数   每个元素记录当前列所需的权重，从行循环。
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        //行数
        for (int i = 1; i < m; i++) {
            //列数
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }
}
