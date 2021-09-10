package leetcode;

/**
 * @author chenfh   题目描述：16进制
 * @date 2021-06-07 10:25
 */
public class Leetcode_405 {
    public static char[] map  ={'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static void main(String[] args) {
        int target = -1;
        System.out.println(base16(target));
    }

    public static String base16(int n) {
        if (n == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        //不考虑符号位
        while (n != 0) {
            //& 操作符取余
            sb.append(map[n & 0b1111]);
            // >>> <<< 无符号移位
            n >>>= 4;
        }
        String result = sb.reverse().toString();
        return result;
    }
}
