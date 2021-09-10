package leetcode;

/**
 * @author chenfh
 * 题目描述：A message containing letters from A-Z can be encoded into numbers using the following mapping:
 * <p>
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the
 * reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
 * <p>
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is
 * different from "06".
 * <p>
 * Given a string s containing only digits, return the number of ways to decode it.
 * <p>
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * <p>
 * Input: s = "06"
 * Output: 0
 * Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
 * @date 2021-06-07 10:25
 */
public class Leetcode_91 {
    public static void main(String[] args) {
        String str = "2222";
        System.out.println(numDecode(str));
    }

    public static int numDecode(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }

        int dp0 = 1;
        int dp1 = 1;
        int res = 1;
        for (int i = 1; i < s.length(); i++) {
            int num = s.charAt(i) - '0';
            char pre = s.charAt(i - 1);
            if (num == 0) {
                if (pre == '1' || pre == '2') {
                    res = dp1;
                } else {
                    return 0;
                }
            } else if ((pre == '1' && num < 10) || (pre == '2' && num < 7)) {
                res = dp0 + dp1;
            } else {
                //无额外解读方案
                res = dp1;
            }
            dp0 = dp1;
            dp1 = res;
        }
        return res;
    }
}
