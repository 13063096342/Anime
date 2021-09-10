package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenfh
 * 题目描述：
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 * <p>
 * If n = 4 and k = 2, a solution is:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * @date 2021-06-07 10:25
 */
public class Leetcode_77 {
    public static void main(String[] args) {
        count0(4, 2);
    }

    public static List<List<Integer>> count0(int n, int k) {
        if (n < 0 || n < k) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        dfsSearch(temp, result, n, k, 1);
        return result;
    }

    private static void dfsSearch(List<Integer> temp, List<List<Integer>> result, int n, int k, int start) {
        if (temp.size() == k) {
            result.add(temp);
            temp.forEach(x -> System.out.print(x + " "));
            System.out.println("\n");
            return;
        }

        for (int i = start; i <= n; i++) {
            temp.add(i);
            dfsSearch(temp, result, n, k, i + 1);
            temp.remove(temp.size() - 1);
        }

    }
}
