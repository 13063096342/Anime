package leetcode;

import com.java.sdk.util.CollectionUtil;

/**
 * @author chenfh
 * 题目描述：
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 * <p>
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 * @date 2021-06-07 10:25
 */
public class Leetcode_474 {
    public static void main(String[] args) {
        System.out.println(count0(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
    }

    /**
     * dp[i][j] 表示最多i个0，j个1的元素个数。
     * arrays[n] 表示第n个字符串
     * dp[i][j] = max{dp[i],[j],dp[i-nums[n]][j-nums[n]]+1}
     */
    public static Integer count0(String[] arrays, int n, int m) {
        if (CollectionUtil.isEmpty(arrays)) {
            return 0;
        }
        int[][] dp = new int[n + 1][m + 1];

        for (String str : arrays) {
            int zero = 0;
            int one = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') {
                    zero++;
                } else {
                    one++;
                }
            }

            for (int i = n; i >= zero; i--) {
                for (int j = m; j >= one; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zero][j - one] + 1);
                }
            }
        }
        return dp[n][m];
    }
}
