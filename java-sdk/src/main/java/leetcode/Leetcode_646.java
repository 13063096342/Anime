package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author chenfh
 * 题目描述：给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
 * 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
 * 给定一个数对集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 * <p>
 * 示例：
 * <p>
 * 输入：[[1,2], [2,3], [3,4]]
 * 输出：2
 * 解释：最长的数对链是 [1,2] -> [3,4]
 * @date 2021-06-07 10:25
 */
public class Leetcode_646 {
    public static void main(String[] args) {
        System.out.println(count0(new int[][]{{1,2},{3,4},{1,2}}));
    }

    public static Integer count0(int[][] nums) {
        Arrays.sort(nums, Comparator.comparingInt(a -> a[0]));
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i][0] > nums[j][1]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }
        return Arrays.stream(dp).max().orElse(0);
    }
}
