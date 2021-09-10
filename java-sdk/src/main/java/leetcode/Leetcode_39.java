package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenfh
 * 题目描述：
 * 给定一个无重复元素的正整数数组candidates和一个正整数target，找出candidates中所有可以使数字和为目标数target的唯一组合。
 * candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。
 *  
 * given candidate set [2, 3, 6, 7] and target 7,
 * A solution set is:
 * [[7],[2, 2, 3]]
 * @date 2021-06-07 10:25
 */
public class Leetcode_39 {

    public static void main(String[] args) {
        System.out.println(count0(new int[]{2, 3, 6, 7}, 7));
    }

    public static Integer count0(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        dfsSearch(result, temp, target, candidates, 0);
        return null;
    }

    private static void dfsSearch(List<List<Integer>> result, List<Integer> temp, int target, int[] candidates, int start) {
        if (target == 0) {
            result.add(temp);
            temp.forEach(x -> System.out.print(x + " "));
            System.out.println("\n");
        }
        if (target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            temp.add(candidates[i]);
            dfsSearch(result, temp, target - candidates[i], candidates, i);
            temp.remove(temp.size() - 1);
        }
    }
}
