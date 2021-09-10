package com.java.sdk.test;

import java.util.Random;

/**
 * @author chenfh
 * @date 2021-08-30 20:34
 */
public class AliTest2 {
    public static Integer[] tempResult = new Integer[10001];
    public static Integer[] nums = new Integer[]{};
    //public static Random random = new Random();

    public static void main(String[] args) {
        //init(nums);
        readNum(nums);
        for (int i = 1; i < tempResult.length; i++) {
            for (int j = 0; j < tempResult[i]; j++) {
                System.out.print(i + " ");
            }
        }
    }

    public static void readNum(Integer[] nums) {
        for (Integer num : nums) {
            tempResult[num] = tempResult[num] + 1;
        }
    }
}
