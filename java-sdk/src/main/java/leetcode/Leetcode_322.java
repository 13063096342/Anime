package leetcode;

/**
 * @author chenfh
 * 题目描述：
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种
 * 硬币组合能组成总金额，返回 -1。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * @date 2021-06-07 10:25
 */
public class Leetcode_322 {
    public static void main(String[] args) {
        System.out.println(count0(new int[]{2, 3, 5}, 11));
    }

    public static Integer count0(int[] nums, int target) {
        int[] dp = new int[target + 1];
        for (int i = 1; i <= target; i++) {
            for (int item : nums) {
                if (item > i) {
                    continue;
                }
                if (i == item) {
                    dp[i] = 1;
                } else if (dp[i] == 0 && dp[i - item] != 0) {
                    dp[i] = dp[i - item] + 1;
                } else if (dp[i - item] != 0) {
                    dp[i] = Math.min(dp[i], dp[i - item] + 1);
                }
            }
        }

        return dp[target];
    }
}
