package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author chenfh
 * 题目描述：
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * @date 2021-06-07 10:25
 */
public class Leetcode_56 {
    public static int[][] nums = new int[][]{
            {1,3},{2,6},{8,10},{15,18}
    };
    public static void main(String[] args) {
        System.out.println(count0(nums));
    }

    public static Integer count0(int[][] nums) {
        Arrays.sort(nums, Comparator.comparing(o -> o[0]));
        List<int[]> result = new ArrayList<>();
        result.add(new int[]{nums[0][0],nums[0][1]});
        int currentHigh = nums[0][1];
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            //当第二个数据的极小值>当前极大值
            if(nums[i][0] > currentHigh) {
                result.add(new int[]{nums[i][0],nums[i][1]});
                currentHigh = nums[i][1];
                index++;
            } else {
                if (nums[i][1] > currentHigh) {
                    result.get(index)[1] = nums[i][1];
                    currentHigh = nums[i][1];
                }
            }
        }

        for (int[] ints : result) {
            System.out.println("[" + ints[0] + "," + ints[1] + "]");
        }
        return null;
    }
}
