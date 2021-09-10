package leetcode;

/**
 * @author chenfh
 * 题目描述：
 * You are given an integer array coins representing coins of different denominations and an integer amount representing
 * a total amount of money.
 * Return the number of combinations that make up that amount. If that amount of money cannot be made up by any
 * combination of the coins, return 0.
 * <p>
 * You may assume that you have an infinite number of each kind of coin.
 * <p>
 * Input: amount = 5, coins = [1,2,5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * @date 2021-06-07 10:25
 */
public class Leetcode_518 {
    public static void main(String[] args) {
        System.out.println(count0(new int[]{2, 3, 5}, 11));
    }

    public static Integer count0(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int item : nums) {
            for (int i = item; i <= target; i++) {
                dp[i] = dp[i] + dp[i - item];

            }
        }
        return dp[target];
    }
}
