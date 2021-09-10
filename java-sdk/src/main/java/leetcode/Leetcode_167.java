package leetcode;

/**
 * @author chenfh   题目描述：在有序数组中找出两个数，使它们的和为 target。并输出下标
 * @date 2021-06-07 10:25
 */
public class Leetcode_167 {
    public static void main(String[] args) {
        int[] source = {2, 3, 7, 11, 15, 19};
        int target = 22;
        int[] result = twoSum(source, target);
        System.out.println("index1：" + result[0] + "   index2:" + result[1]);
    }

    public static int[] twoSum(int[] source, int target) {
        if (source == null || source.length == 0) {
            return new int[]{0, 0};
        }
        int i = 0;
        int j = source.length - 1;
        int temp;
        while (i < j) {
            temp = source[i] + source[j];
            if (temp == target) {
                return new int[]{i + 1, j + 1};
            }
            if (temp > target) {
                j--;
            } else {
                i++;
            }
        }
        return new int[]{0, 0};
    }
}
