package offer;

import java.util.Stack;

/**
 * 题目描述：输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。
 * <p>
 * 例如序列 1,2,3,4,5 是某栈的压入顺序，序列 4,5,3,2,1 是该压栈序列对应的一个弹出序列，
 * 但 4,3,5,1,2 就不可能是该压栈序列的弹出序列。
 *
 * @author chenfh
 * @date 2021-08-20 17:23
 */
public class offer_31 {

    public static void main(String[] args) {
        System.out.println(result(new int[]{ 1,2,3,4,5 },new int[]{ 4,3,5,1,2}));
    }

    public static boolean result(int[] pushList, int[] popList) {
        Stack<Integer> stack = new Stack<>();
        int length = pushList.length;
        for (int pushIndex = 0, popIndex = 0; pushIndex < length; pushIndex++) {
            stack.push(pushList[pushIndex]);
            while (!stack.empty() && popList[popIndex] == stack.peek()) {
                stack.pop();
                popIndex++;
            }
        }
        return stack.empty();
    }
}
