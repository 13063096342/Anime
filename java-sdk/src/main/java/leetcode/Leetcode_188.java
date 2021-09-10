package leetcode;

import java.util.Arrays;

/**
 * @author chenfh
 * 题目描述：
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 * 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 * @date 2021-06-07 10:25
 */
public class Leetcode_188 {
    public static void main(String[] args) {
        System.out.println(count0(new int[]{2, 4, 1}, 2));
    }

    /**
     * 用buy[i][j]表示对于数组prices[0..i]中的价格而言，进行恰好j笔交易，并且当前手上持有一支股票，这种情况下的最大利润；
     * 用sell[i][j]表示恰好进行j笔交易，并且当前手上不持有股票，这种情况下的最大利润。
     * <p>
     * 转移方程：
     * buy[i][j]=max{buy[i−1][j],sell[i−1][j]−price[i]}
     * sell[i][j]=max{sell[i−1][j],buy[i−1][j−1]+price[i]}
     * <p>
     * 由于在所有的 n 天结束后，手上不持有股票对应的最大利润一定是严格由于手上持有股票对应的最大利润的，然而完成的交易数并
     * 不是越多越好(prices单调递减，我们不进行任何交易才是最优的)，因此最终的答案即为sell[n−1][0..k] 中的最大值。
     */
    public static Integer count0(int[] nums, int k) {
        if (nums.length < 2) {
            return 0;
        }
        int result = 0;
        if (k >= (nums.length + 1) / 2) {
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > nums[i - 1]) {
                    result += nums[i] - nums[i - 1];
                }
            }
            return result;
        }

        int[][] buy = new int[nums.length][k + 1];
        int[][] sell = new int[nums.length][k + 1];

        for (int i = 1; i < nums.length; i++) {
            buy[i][0] = Math.max(-nums[i], -nums[i]);
            for (int j = 1; j <= k; j++) {
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - nums[i]);
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + nums[i]);
            }
        }
        return Arrays.stream(sell[nums.length - 1]).max().getAsInt();
    }
}
