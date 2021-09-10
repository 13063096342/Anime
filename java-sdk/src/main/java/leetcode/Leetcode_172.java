package leetcode;

/**
 * @author chenfh
 * 题目描述：
 * 统计阶乘n有几个0 => n阶层中有有几个5
 * @date 2021-06-07 10:25
 */
public class Leetcode_172 {
    public static void main(String[] args) {
        System.out.println(count0(25));
    }

    public static Integer count0(int n) {
        return n == 0 ? 0 : n/5 + count0(n/5);
    }

    public static Integer count0(int[] nums) {
        return null;
    }
}
