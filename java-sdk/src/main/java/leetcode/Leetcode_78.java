package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenfh
 * 题目描述：
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）-- 不重复
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * @date 2021-06-07 10:25
 */
public class Leetcode_78 {
    public static void main(String[] args) {
        //System.out.println(count0(new int[]{1, 2, 2}));
        System.out.println(Math.pow(1.1, 60));
    }

    public static Integer count0(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            dfsSearch(temp, result, nums, i, 0);
        }
        return null;
    }

    private static void dfsSearch(List<Integer> temp, List<List<Integer>> result, int[] nums, int n, int start) {
        if (temp.size() == n) {
            result.add(temp);
            System.out.print("[");
            temp.forEach(x -> System.out.print(x + " "));
            System.out.print("]");
            System.out.println("\n");
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            temp.add(nums[i]);
            dfsSearch(temp, result, nums, n, i + 1);
            temp.remove(temp.size() - 1);

        }
    }
}
