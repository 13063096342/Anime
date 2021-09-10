package leetcode;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author chenfh   题目描述：反转字符串中的元音字符
 * @date 2021-06-07 10:25
 */
public class Leetcode_345 {
    private final static HashSet<Character> vowels = new HashSet<>(
            Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    public static void main(String[] args) {
        System.out.println("result：" + resert("leetcode"));
    }

    public static String resert(String s) {
        char[] result = new char[s.length()];
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            char a = s.charAt(i);
            char b = s.charAt(j);
            if (!vowels.contains(a)) {
                result[i++] = a; } else if (!vowels.contains(b)) {
                result[j--] = b;
            } else {
                result[i++] = b;
                result[j--] = a;
            }
            System.out.println("step :"+new String(result));
        }
        return new String(result);
    }
}
