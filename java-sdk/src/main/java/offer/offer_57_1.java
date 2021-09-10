package offer;

import java.util.ArrayList;

/**
 * 题目描述：在有序数组中找出两个数，使得和为给定的数 S。如果有多对数字的和等于 S，输出两个数的乘积最小的。
 *
 * @author chenfh
 * @date 2021-08-20 17:23
 */
public class offer_57_1 {

    public static void main(String[] args) {
        ArrayList result = result(new int[]{1, 2, 3, 4, 5}, 6);
        System.out.println();
    }

    public static ArrayList result(int[] nums, int target) {
        int n = nums.length;
        int j = 0;
        int multi = 0;
        ArrayList result = new ArrayList<>();
        while (j < n) {
            int total = nums[j] + nums[n];
            if (total == target) {
                result.add(nums[n]);
                result.add(nums[j]);
                j++;
                n--;
                multi = nums[j] * nums[n];
            } else if (total > target) {
                n--;
            } else {
                j++;
            }
        }
        return result;
    }
}
