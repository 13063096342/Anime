package leetcode;

/**
 * @author chenfh   题目描述：判断一个数是不是平方数，并输出平方根
 * @date 2021-06-07 10:25
 */
public class Leetcode_367 {
    public static void main(String[] args) {
        int target = 26;
        System.out.println(sqrtNum(target));
    }

    public static Integer sqrtNum(int n) {
        //迭代算法比较弱势，需要加强
        int start = 1;
        while (n > 0) {
            n -= start;
            start += 2;
        }
        if (n == 0) {
            return (start - 1) / 2;
        }
        return -1;
    }
}
