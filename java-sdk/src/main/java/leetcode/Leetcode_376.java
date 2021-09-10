package leetcode;

/**
 * @author chenfh
 * 题目描述：如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。第一个差（如果存在的话）可能是正数或负数。
 * 仅有一个元素或者含两个不等元素的序列也视作摆动序列。
 * 例如， [1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3) 是正负交替出现的。
 * 相反，[1, 4, 7, 2, 5] 和 [1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个
 * 差值为零。
 * 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
 * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。
 */
public class Leetcode_376 {
    public static void main(String[] args) {
        System.out.println(count0(new int[]{1,17,5,10,13,15,10,5,16,8}));
    }

    public static Integer count0(int[] n) {
        int up = 1;
        int down = 1;
        //up是否加1取决down的当前值
        for (int i = 1; i < n.length; i++) {
            if (n[i] > n[i - 1]) {
                up = down + 1;
            } else if (n[i] < n[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }
}
