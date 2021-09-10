package leetcode;

/**
 * @author chenfh
 * 题目描述：
 * Partition Equal Subset Sum (Medium)
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 0/1背包问题
 * 有一个容量为 N 的背包，要用这个背包装下物品的价值最大，这些物品有两个属性：体积 w 和价值 v。
 * 定义一个二维数组 dp 存储最大价值，其中 dp[i][j] 表示前 i 件物品体积不超过 j 的情况下能达到的最大价值。设第 i 件物品体积为 w，价值为 v，
 * 根据第 i 件物品是否添加到背包中，可以分两种情况讨论：
 * 第 i 件物品没添加到背包，总体积不超过 j 的前 i 件物品的最大价值就是总体积不超过 j 的前 i-1 件物品的最大价值，dp[i][j] = dp[i-1][j]。
 * 第 i 件物品添加到背包中，dp[i][j] = dp[i-1][j-w] + v。
 * 第 i 件物品可添加也可以不添加，取决于哪种情况下最大价值更大。因此，0-1 背包的状态转移方程为：
 * dp[i][j] = max(dp[i-1][j],dp[i-1][i-w]+v)
 * <p>
 * 空间优化
 * 在程序实现时可以对 0-1 背包做优化。观察状态转移方程可以知道，前 i 件物品的状态仅与前 i-1 件物品的状态有关，因此可以将 dp 定义为一维
 * 数组，其中 dp[j] 既可以表示 dp[i-1][j] 也可以表示 dp[i][j]。此时:
 * dp[j] = max(dp[j],dp[j-w]+v),因为 dp[j-w] 表示 dp[i-1][j-w]，因此不能先求 dp[i][j-w]，防止将 dp[i-1][j-w] 覆盖。也就是说要先计算 dp[i][j] 再计算 dp[i][j-w]，在程序实现时需要按倒序来循环求解。
 * @date 2021-06-07 10:25
 */
public class Leetcode_416 {
    public static void main(String[] args) {
        System.out.println(count0(new int[]{1,2,3,4,5,6,7,1,1}));
    }

    public static boolean count0(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }

        //背包容量为w,且一定要放满。
        int w = sum / 2;

        int[] dp = new int[w + 1];
        for (int num : nums) {
            for (int i = w; i > 0; i--) {
                if (i >= num) {
                    dp[i] = Math.max(dp[i], dp[i - num] + num);
                }
            }
        }
        return dp[w] == w;
    }
}
