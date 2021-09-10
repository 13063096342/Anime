package leetcode;

/**
 * @author chenfh   题目描述：你是一个计划抢劫沿环形街房屋的职业抢劫犯。每家每户都有一定的私房钱，阻止你抢劫每
 * 家每户的唯一限制是相邻的房屋都有连接的安全系统，如果相邻的房屋在同一晚被闯入，它会自动联系警察。
 * 给定一个整数数组数字代表每户人家的钱数，在不报警的情况下归还你今晚可以抢劫的最大钱数。
 * <p>
 * Example:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * @date 2021-06-07 10:25
 */
public class Leetcode_213 {
    public static void main(String[] args) {
        System.out.println(result(new int[]{2, 7, 9, 3, 1}));
    }

    public static Integer result(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(result(nums, 0, nums.length - 2), result(nums, 1, nums.length - 1));
    }


    public static Integer result(int[] nums, int from, int to) {
        //max(f(n-2) + num[n],f(n-1))  环形街 = 抢劫范围0->n-2 || 1->n-1
        int result = 0;
        int pre1 = 0;
        int pre2 = 0;
        for (int i = from; i < to; i++) {
            result = Math.max(pre2 + nums[i], pre1);
            pre2 = pre1;
            pre1 = result;
        }
        return result;
    }
}
