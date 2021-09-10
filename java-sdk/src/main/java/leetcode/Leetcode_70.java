package leetcode;

/**
 * @author chenfh   题目描述：有 N 阶楼梯，每次可以上一阶或者两阶，求有多少种上楼梯的方法。--斐波那契数列
 * @date 2021-06-07 10:25
 */
public class Leetcode_70 {
    public static void main(String[] args) {
        System.out.println(result(4));
    }

/*    public static Integer result(int n) {
        //暴力破解
        if (n <= 2 ) {
            return n;
        }
        return result(n-1) + result(n -2);
    }*/

    public static Integer result(int n) {
        if (n <= 2) {
            return n;
        }
        int cur = 0;
        int pre1 = 2;
        int pre2 = 1;
        for (int i = 3; i <= n; i++) {
            cur = pre1 + pre2;
            pre2 = pre1;
            pre1 = cur;
        }
        return cur;
    }
}
