package leetcode;

/**
 * @author chenfh   题目描述：给定一个正整数，返回它在 Excel 表中相对应的列名称。
 * @date 2021-06-07 10:25
 */
public class Leetcode_168 {
    public static void main(String[] args) {
        int target = 1;
        System.out.println(base26(target));
    }

    public static String base26(int n) {
        StringBuilder a = new StringBuilder();
        while (n != 0) {
            n--;
            a.append((char)(n % 26 + 'A'));
            n = n/26;
        }
        return a.reverse().toString();
    }
}
