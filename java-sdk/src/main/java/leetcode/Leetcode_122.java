package leetcode;

/**
 * @author chenfh
 * 题目描述：
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * **注意：**你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * @date 2021-06-07 10:25
 */
public class Leetcode_122 {
    public static void main(String[] args) {
        System.out.println(count0(new int[]{1,2,3,4,5,6,5,4,5,3,1,5}));
    }

    public static Integer count0(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        int sale = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]){
                sale += nums[i] - nums[i-1];
            }
        }
        return sale;
    }

}
