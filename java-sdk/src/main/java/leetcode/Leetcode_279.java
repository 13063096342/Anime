package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenfh   题目描述：给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。=> U - a = f(x) - a^2  U为整数方根集合，f(x)为当前数
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 * @date 2021-06-07 10:25
 */
public class Leetcode_279 {
    public static void main(String[] args) {
        System.out.println(count0(16));
    }

    public static Integer count0(int n) {
        List<Integer> sqrtList = getSqrtList(n);
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (Integer sqrtNum : sqrtList) {
                if (sqrtNum > i) {
                    break;
                }
                min = Math.min(dp[i - sqrtNum] + 1, min);
                dp[i] =min;
            }
        }
        return dp[n];
    }

    private static List<Integer> getSqrtList(int n) {
        int start = 1;
        int step = 1;
        List<Integer> sqrtList = new ArrayList<>();
        while (start <= n) {
            sqrtList.add(start);
            step += 2;
            start += step;
        }
        return sqrtList;
    }
}
