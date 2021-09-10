package leetcode;

/**
 * @author chenfh   题目描述：转7进制
 * @date 2021-06-07 10:25
 */
public class Leetcode_504 {
    public static void main(String[] args) {
        int target = 12;
        System.out.println(base7(target));
    }

    public static String base7(int n) {
        if (n == 0) {
            return "0";
        }
        boolean flag = true;
        if (n < 0) {
            n = -n;
            flag = false;
        }
        StringBuffer sb = new StringBuffer();
        while (n > 0) {
            sb.append(n % 7);
            n /= 7;
        }
        String result = sb.reverse().toString();
        return flag ? result: "-" + result;
    }
}
