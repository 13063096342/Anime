package leetcode;

import java.util.HashMap;

/**
 * @author chenfh
 * 题目描述：Given an array of size n, find the majority element.The majority element is the element that
 * appears more than ⌊ n/2 ⌋ times. You may assume that the array is non-empty and the majority element
 * always exist in the array.
 * @date 2021-06-07 10:25
 */
public class Leetcode_169 {
    public static void main(String[] args) {
        System.out.println(query(new int[]{1, 23, 4, 5, 6, 5}));
    }

    public static Integer query(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer num : nums) {
            Integer newValue = map.getOrDefault(num, 0) + 1;
            if (newValue > nums.length / 2) {
                return num;
            }
            map.put(num, newValue);
        }
        return null;
    }

    /**
     * 摩尔投票法
     * 摩尔投票算法的思想是找出一组不同的元素，将其从数组中删除。无限执行上述步骤后，最后只会剩下一个元素
     * （如果有元素次数大于n/2的话）
     * 具体实现：按照数组原来的顺序进行投票，删除。
     * 首先将nums[0]作为候选元素
     * 设置count值，遍历数组。以count++表示当前元素与候选元素相同，该候选元素票数增加1；
     * 以count–表示当前选出的元素是不同元素，候选元素减1票（相当于删除了两个不同的元素）；
     * 当count == 0时，候选元素换人（表明前一阶段并没有出现次数超过半数的元素，这样我们只需要求解原始问题的
     * 子问题即可）
     */
    public int majorityElement3(int[] nums) {
        int count = 0, ret = 0;
        for (int num : nums) {
            if (count == 0) {
                ret = num;
            }
            if (num != ret) {
                count--;
            } else {
                count++;
            }
        }
        return ret;
    }
}
