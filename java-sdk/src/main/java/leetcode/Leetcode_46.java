package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenfh
 * 题目描述：
 * 输入数组所有组合
 * <p>
 * example:
 * [1,2,3] have the following permutations:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * @date 2021-06-07 10:25
 */
public class Leetcode_46 {
    public static int[] nums = new int[]{1, 2, 3};

    public static void main(String[] args) {
        System.out.println(count0(nums));
    }

    public static Integer count0(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        getResult(visited, temp, result);
        return 0;
    }

    private static void getResult(boolean[] visited, List<Integer> temp, List<List<Integer>> result) {
        if (temp.size() == nums.length) {
            result.add(temp);
            temp.forEach(x -> System.out.print(x + " "));
            System.out.println();
            return;
        }
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            temp.add(nums[i]);
            getResult(visited, temp, result);
            visited[i] = false;
            temp.remove(temp.size() - 1);
        }
    }
}
