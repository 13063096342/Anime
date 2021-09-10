package offer;

import java.util.*;

/**
 * 题目描述：给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 *
 * 例如，如果输入数组 {2, 4, 4, 2, 6, 2, 5, 1} 及滑动窗口的大小 3，那么一共存在 6 个滑动窗口，他们的最大值分别为 {
 * 4, 4, 6, 6, 6, 5}。
 * @author chenfh
 * @date 2021-08-20 17:23
 */
public class offer_59 {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 4, 2, 6, 2, 5, 1};
        List<Integer> result = result(nums, 3);
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
    }

    public static List<Integer> result(int[] nums,int size) {
        ArrayList<Integer> result = new ArrayList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < size; i++) {
            queue.add(nums[i]);
        }
        result.add(queue.peek());
        for (int i = size; i < nums.length; i++) {
            queue.remove(nums[i-size]);
            queue.add(nums[i]);
            result.add(queue.peek());
        }
        return result;
    }
}
