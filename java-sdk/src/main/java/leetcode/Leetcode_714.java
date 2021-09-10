package leetcode;

/**
 * @author chenfh
 * 题目描述：
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
 * <p>
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 * <p>
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 * <p>
 * 输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出：8
 * 解释：能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8
 * @date 2021-06-07 10:25
 */
public class Leetcode_714 {
    public static void main(String[] args) {
        System.out.println(count0(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }

    /**
     * 定义dp[i][0]为第i天，未持有股票时的收益，dp[i][1]为第i天，持有股票时的收益。
     * 则：
     * dp[i][0]=max{dp[i-1][0],dp[i-1][1]+price[i]}
     * dp[i][1]=max{dp[i-1][1],dp[i-1][0]-price[i]}
     *
     * dp[i][0]为所求
     */
    public static Integer count0(int[] nums, int fee) {
        if (nums.length < 2) {
            return 0;
        }
        int buy;
        int preBuy = -nums[0];
        int sell = 0;
        int preSell = 0;

        //dp[i-1][0]=preSell  dp[i-1][1] = preBuy
        for (int i = 1; i < nums.length; i++) {
            buy = Math.max(preBuy,preSell - nums[i]);
            sell = Math.max(preSell, preBuy + nums[i] - fee);
            preBuy = buy;
            preSell = sell;
        }

        return sell;
    }
}
