package leetcode;

import com.java.sdk.util.StringUtil;
import org.apache.poi.ss.formula.functions.Count;

/**
 * @author chenfh
 * 题目描述：
 * Letter Combinations of a Phone Number (Medium)
 * <p>
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * @date 2021-06-07 10:25
 */
public class Leetcode_17 {
    private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static void main(String[] args) {
        count0("23");
        System.out.println();
    }

    public static void count0(String nums) {
        if (StringUtil.isEmpty(nums)) {
            return;
        }
        dsf(new StringBuilder(), nums);
        return;
    }

    public static void dsf(StringBuilder pre, String nums) {
        if (pre.length() == nums.length()) {
            System.out.print(pre+" ");
            return;
        }
        char c = nums.charAt(pre.length());
        String key = KEYS[c - '0'];
        for (char a : key.toCharArray()) {
            pre.append(a);
            dsf(pre, nums);
            pre.deleteCharAt(pre.length() - 1);
        }
    }
}
