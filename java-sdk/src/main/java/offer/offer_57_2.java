package offer;

import java.util.ArrayList;

/**
 * 题目描述：和为 S 的连续正数序列
 *
 * 输出所有和为 S 的连续正数序列。例如和为 100 的连续序列有：
 * [9, 10, 11, 12, 13, 14, 15, 16]
 * [18, 19, 20, 21, 22]。
 *
 * @author chenfh
 * @date 2021-08-20 17:23
 */
public class offer_57_2 {

    public static void main(String[] args) {
        ArrayList<ArrayList> result = result(15);
        result.forEach(x->{
            for (int i = 0; i < x.size(); i++) {
                System.out.print(x.get(i)+" ");
            }
            System.out.println("\n");
        });
    }

    public static ArrayList<ArrayList> result(int target) {
        int start = 1;
        int end = 2;
        int sum = 3;
        int limit = (target + 1) / 2;
        ArrayList<ArrayList> result = new ArrayList<>();
        while (end <= limit) {
            if (sum > target) {
                sum -= start;
                start++;
            } else if (sum <target) {
                end++;
                sum += end;
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = start; i <= end; i++){
                    list.add(i);
                }
                result.add(list);
                sum -= start;
                start++;
                end++;
                sum += end;
            }
        }
        return result;
    }
}
