package leetcode;

/**
 * @author chenfh
 * 题目描述：Longest Common Subsequence
 * @date 2021-06-07 10:25
 */
public class Leetcode_1143 {
    public static void main(String[] args) {
        System.out.println(count0("abcde", "ace"));
    }

    /**
     * 对于两个子序列 S1 和 S2，找出它们最长的公共子序列。
     * <p>
     * 定义一个二维数组 dp 用来存储最长公共子序列的长度，其中 dp[i][j] 表示 S1 的前 i 个字符与 S2 的前 j 个字符最长公共子序列的长度。
     * 考虑 S1i 与 S2j 值是否相等，分为两种情况：
     * 当 S1i==S2j 时，那么就能在 S1 的前 i-1 个字符与 S2 的前 j-1 个字符最长公共子序列的基础上再加上 S1i 这个值，最长公共子序
     * 列长度加 1，即 dp[i][j] = dp[i-1][j-1] + 1。
     * 当 S1i != S2j 时，此时最长公共子序列为 S1 的前 i-1 个字符和 S2 的前 j 个字符最长公共子序列，或者 S1 的前 i 个字符和 S2
     * 的前 j-1 个字符最长公共子序列，取它们的最大者，即 dp[i][j] = max{ dp[i-1][j], dp[i][j-1] }。
     */
    public static Integer count0(String a, String b) {
        //边界条件略去
        int n = a.length(), m = b.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }
        return dp[n][m];
    }
}
