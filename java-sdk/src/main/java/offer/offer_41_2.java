package offer;

import java.util.*;

import static leetcode.Leetcode_56.nums;

/**
 * 题目描述：请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符 "go" 时，第一个只出现一次的字符是 "g"。
 * 当从该字符流中读出前六个字符“google" 时，第一个只出现一次的字符是 "l"。
 *
 * @author chenfh
 * @date 2021-08-20 17:23
 */
public class offer_41_2 {
    public static void main(String[] args) {
        Character[] chars = new Character[]{'g','g','o','o','l','e','l','a'};
        System.out.println(result(chars));
    }

    public static Character result(Character[] chars) {

        LinkedHashMap<Character, Integer> linkedHashMap = new LinkedHashMap<>();

        for (Character c : chars) {
            Integer now = linkedHashMap.getOrDefault(c, 0);
            if (now > 1) {
                continue;
            }
            linkedHashMap.put(c, linkedHashMap.getOrDefault(c, 0) + 1);
        }
        for(Map.Entry<Character, Integer> entry : linkedHashMap.entrySet()) {
            if (entry.getValue().equals(1)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
