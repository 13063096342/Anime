package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenfh
 * 题目描述：
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * <p>
 * Input: k = 3, n = 9
 * Output:
 * [[1,2,6], [1,3,5], [2,3,4]]
 * @date 2021-06-07 10:25
 */
public class Leetcode_216 {
    public static void main(String[] args) {
        System.out.println(count0(3, 9));
    }

    public static Integer count0(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        dfsSearch(temp, result, k, n, 1);
        return null;
    }

    private static void dfsSearch(List<Integer> temp, List<List<Integer>> result, int k, int n, int start) {
        if (n == 0 && k == 0) {
            result.add(temp);
            temp.forEach(x -> System.out.print(x + " "));
            System.out.println("\n");
            return;
        }

        for (int i = start; i <= 9; i++) {
            if (i <= n && k >= 1) {
                temp.add(i);
                dfsSearch(temp, result, k - 1, n - i, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
