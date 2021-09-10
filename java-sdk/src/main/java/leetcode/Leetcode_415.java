package leetcode;

/**
 * @author chenfh   题目描述：add String
 * @date 2021-06-07 10:25
 */
public class Leetcode_415 {
    public static void main(String[] args) {
        System.out.println(addString("1234", "6578"));
    }

    public static String addString(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0 || carry == 1) {
            if (i >= 0 && a.charAt(i) > '0') {
                carry = carry + a.charAt(i--) - '0';
            }
            if (j >= 0 && b.charAt(j) > '0') {
                carry = carry + b.charAt(j--) - '0';
            }
            sb.append(carry % 10);
            carry = carry / 10;
        }
        return sb.reverse().toString();
    }
}
