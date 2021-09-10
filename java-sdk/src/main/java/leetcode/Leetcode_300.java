package leetcode;

import java.util.Arrays;

/**
 * @author chenfh   题目描述：给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 * @date 2021-06-07 10:25
 */
public class Leetcode_300 {
    public static void main(String[] args) {
        System.out.println(count0(new int[]{4,5,6,7,8,9,1,2,3}));
    }

    public static Integer count0(int[] nums) {
        int[] dp = new int[nums.length];
        //初始子序列为1
        Arrays.fill(dp,1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1,dp[i]);
                }
            }
        }
        //还要遍历一次dp[]查找最大值
        return Arrays.stream(dp).max().orElse(0);
    }
}
