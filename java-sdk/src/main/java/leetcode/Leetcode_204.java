package leetcode;

/**
 * @author chenfh   题目描述：输出小于n的所有质数数量
 * @date 2021-06-07 10:25
 */
public class Leetcode_204 {
    public static void main(String[] args) {
        int target = 12;
        System.out.println(count(target));
    }

    public static Integer count(int n) {
        if (n < 3) {
            return 1;
        }
        boolean[] target = new boolean[n+1];
        Integer count = 0;
        for (int i = 2; i < n; i++) {
            if (target[i]) {
                continue;
            }
            count++;
            for (int j = i * i; j < n; j += i) {
                target[j] = true;
            }
        }
        return count;
    }
}
