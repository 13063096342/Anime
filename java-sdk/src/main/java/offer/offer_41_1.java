package offer;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 题目描述：数据流中的中位数
 *
 * @author chenfh
 * @date 2021-08-20 17:23
 */
public class offer_41_1 {
    /**
     * PriorityQueue默认小队顶
     */
    public static PriorityQueue<Integer> min = new PriorityQueue<>(Comparator.comparingInt(o -> o));
    public static PriorityQueue<Integer> max = new PriorityQueue<>((o1,o2)->o2-o1);
    public static Integer[] nums = new Integer[]{1,2,3,4,5,6,7};

    public static void main(String[] args) {
        for (int i = 0; i < nums.length; i++) {
            addNums(nums[i],i);
        }
        System.out.println(getMid(nums.length));
    }

    public static void addNums(int num,int total) {
        //让最大堆和最小堆平衡  最大堆存小值   最小堆存大值
        //需要先将元素插入左半边，然后利用左半边为大顶堆的特点，取出堆顶元素即为最大元素，此时插入右半边
        if (total % 2 == 0) {
            max.add(num);
            min.add(max.poll());
        } else {
            min.add(num);
            max.add(min.poll());
        }
    }

    public static Double getMid(int total) {
        if (total % 2 == 1) {
            return (double)min.peek();
        }
        return (min.peek()+max.peek())/2.0;
    }



}
