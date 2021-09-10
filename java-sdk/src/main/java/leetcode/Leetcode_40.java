package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenfh
 * 题目描述：
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 注意：解集不能包含重复的组合。 
 *  
 * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
 * A solution set is:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * @date 2021-06-07 10:25
 */
public class Leetcode_40 {

    public static void main(String[] args) {
        System.out.println(count0(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
    }

    public static Integer count0(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(candidates);
        dfsSearch(result, temp, target, candidates, 0);
        return null;
    }

    private static void dfsSearch(List<List<Integer>> result, List<Integer> temp, int target, int[] candidates,
                                  int start) {
        if (target == 0) {
            result.add(temp);
            temp.forEach(x -> System.out.print(x + " "));
            System.out.println("\n");
        }

        for (int i = start; i < candidates.length && target >= candidates[i]; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            temp.add(candidates[i]);
            dfsSearch(result, temp, target - candidates[i], candidates, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}
