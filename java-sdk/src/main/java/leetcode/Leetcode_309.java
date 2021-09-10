package leetcode;

/**
 * @author chenfh
 * 题目描述：
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * **注意：**你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * @date 2021-06-07 10:25
 */
public class Leetcode_309 {
    public static void main(String[] args) {
        System.out.println(count0(new int[]{1, 2, 3, 4, 5, 6, 5, 4, 5, 3, 1, 5}));
    }

    /**
     * 三个状态 buy sell freeze
     * <p>
     * 状态改变：
     * freeze[i] = sell[i-1]
     * sell[i] = max{sell[i-1],buy[i-1]+price[i]}
     * buy[i] = max{-buy[i-1],freeze[i-1]-price[i]}
     *
     */
    public static Integer count0(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        int[] buys = new int[nums.length];
        int[] sells = new int[nums.length];
        int[] freezes = new int[nums.length];

        freezes[0] = 0;
        sells[0] = 0;
        buys[0] = -nums[0];
        for (int i = 1; i < nums.length; i++) {
            freezes[i] = sells[i - 1];
            buys[i] = Math.max(buys[i - 1], freezes[i - 1] - nums[i]);
            sells[i] = Math.max(sells[i - 1], buys[i - 1] + nums[i]);
        }
        return sells[nums.length-1];
    }

}
