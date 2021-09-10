package leetcode;

/**
 * @author chenfh
 * 题目描述：
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * 注意你不能在买入股票前卖出股票。
 * @date 2021-06-07 10:25
 */
public class Leetcode_121 {
    public static void main(String[] args) {
        System.out.println(count0(new int[]{1,2,3,4,5,6}));
    }

    public static Integer count0(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        int buy = -nums[0];
        int sale = 0;

        for (int i = 1; i < nums.length; i++) {
            buy = Math.max(buy, -nums[i]);
            sale = Math.max(sale, buy + nums[i]);
        }
        return sale;
    }

}
