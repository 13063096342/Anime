package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenfh
 * 题目描述：
 * 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。你需要给出所有可能的组合的结果。
 * 有效的运算符号包含 +, - 以及 * 。
 * <p>
 * Input: "2-1-1".
 * <p>
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * <p>
 * Output : [0, 2]
 * @date 2021-06-07 10:25
 */
public class Leetcode_241 {

    public static void main(String[] args) {
        String input = "2*3-4*5";
        List<Integer> integers = count0(input);
        integers.forEach(System.out::println);
    }

    public static List<Integer> count0(String input) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            //遇到符号时拆分左右
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = count0(input.substring(0, i));
                List<Integer> right = count0(input.substring(i + 1));
                for (Integer l : left) {
                    for (Integer r : right) {
                        switch (c) {
                            case '+':
                                result.add(l + r);
                                break;
                            case '-':
                                result.add(l - r);
                                break;
                            case '*':
                                result.add(l * r);
                                break;
                            default:
                                break;
                        }
                    }
                }

            }
        }
        if (result.size() == 0) {
            result.add(Integer.parseInt(input));
        }
        return result;
    }
}
