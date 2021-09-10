package leetcode;

/**
 * @author chenfh   题目描述：Add Binary (Easy)
 * @date 2021-06-07 10:25
 */
public class Leetcode_67 {
    public static void main(String[] args) {
        System.out.println(addBinary("101", "101101"));
    }

    public static String addBinary(String a, String b) {
        int n1 = a.length() - 1;
        int n2 = b.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (n1 >= 0 || n2 >= 0 || carry == 1) {
            if (n1 >= 0 && a.charAt(n1--) > '0') {
                carry++;
            }
            if (n2 >= 0 && b.charAt(n2--) > '0') {
                carry++;
            }
            sb.append(carry % 2);
            carry = carry / 2;
        }
        return sb.reverse().toString();
    }
}
