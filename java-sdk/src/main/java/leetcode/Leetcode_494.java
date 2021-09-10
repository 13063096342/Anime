package leetcode;

/**
 * @author chenfh
 * 题目描述：
 * 给你一个整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * <p>
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * <p>
 * Adds - Subs  + 2Subs = target + 2Subs
 * All = 2Subs + target => Sub = (All - target)/2
 * @date 2021-06-07 10:25
 */
public class Leetcode_494 {
    public static void main(String[] args) {
        System.out.println(count0(new int[]{1,1,1,1,1},3));
    }

    /**
     * 0/1背包问题：
     * dp[]size为重量数,dp[i]表示重量为i时有几种放法。
     * dp[n] = dp[n]+dp[n-nums[i]]
     *
     * 确定递推公式
     *
     * 有哪些来源可以推出dp[j]呢？
     * 不考虑nums[i]的情况下，填满容量为j - nums[i]的背包，有dp[j - nums[i]]中方法。
     * 那么只要搞到nums[i]的话，凑成dp[j]就有dp[j - nums[i]] 种方法。
     * 举一个例子,nums[i] = 2，dp[3]，填满背包容量为3的话，有dp[3]种方法。
     * 那么只需要搞到一个2（nums[i]），有dp[3]方法可以凑齐容量为3的背包，相应的就有多少种方法可以凑齐容量为5的背包。
     * 那么需要把 这些方法累加起来就可以了，dp[i] += dp[j - nums[i]]
     * 所以求组合类问题的公式，都是类似这种：
     * dp[j] += dp[j - nums[i]]
     *
     */
    public static Integer count0(int[] nums, int target) {
        int total = getTotal(nums) - target;
        if ((total & 1) == 1) {
            return 0;
        }
        int subTotal = total / 2;

        int[] dp = new int[subTotal + 1];
        dp[0] = 1;
        //从nums挑选数字和为subTotal一共有几种方法
        for (int num : nums) {
            for (int j = subTotal; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[subTotal];
    }


    private static int getTotal(int[] nums) {
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }
        return total;
    }
}
