package leetcode;

import java.util.Arrays;

/**
 * @author chenfh   题目描述：给定一个数组，创建一个新数组，新数组的每个元素为原始数组中除了该位置上的元素之外所有元素的乘积。
 * For example, given [1,2,3,4], return [24,12,8,6].不可用除法
 * @date 2021-06-07 10:25
 */
public class Leetcode_238 {
    public static void main(String[] args) {
        int[] result = result(new int[]{2, 4, 6, 8});
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    public static int[] result(int[] nums) {
        int n = nums.length;
        int[] results = new int[n];
        Arrays.fill(results, 1);
        int left = 1;
        for (int i = 1; i < n; i++) {
            left = left * nums[i - 1];
            results[i] = left;
        }
        int right = 1;
        for (int i = n - 2; i >= 0; i--) {
            right = right * nums[i + 1];
            results[i] = results[i] * right;
        }
        return results;
    }
}
