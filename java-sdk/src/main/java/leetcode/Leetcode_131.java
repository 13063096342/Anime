package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenfh
 * 题目描述：
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * 回文串 是正着读和反着读都一样的字符串。
 * <p>
 * For example, given s = "aab",
 * Return：
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 * @date 2021-06-07 10:25
 */
public class Leetcode_131 {
    public static void main(String[] args) {
        System.out.println(count0("aab"));
    }

    public static Integer count0(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        dfsSearch(temp, result, s);
        return null;
    }

    private static void dfsSearch(List<String> temp, List<List<String>> result, String s) {
        if (s.length() == 0) {
            result.add(temp);
            System.out.print("[");
            temp.forEach(x -> {
                System.out.print(x + " ");
            });
            System.out.print("]");
            System.out.println("\n");
        }

        for (int i = 0; i < s.length(); i++) {
            String te = s.substring(0, i + 1);
            if (isPalindrome(te)) {
                temp.add(te);
                dfsSearch(temp, result, s.substring(i + 1));
                temp.remove(temp.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String te) {
        int end = te.length() - 1;
        int begin = 0;
        while (end > begin) {
            if (te.charAt(end--) != te.charAt(begin++)) {
                return false;
            }
        }
        return true;
    }
}
