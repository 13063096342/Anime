package com.java.sdk.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author chenfh
 * @date 2021-08-30 20:21
 */
public class AliTest1 {
    public static Random random = new Random();

    public static void main(String[] args) {
        BigDecimal total = new BigDecimal(100);
        Integer peopleNum = 10;
        ArrayList<BigDecimal> result = cal(total, peopleNum);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    public static ArrayList<BigDecimal> cal(BigDecimal total, Integer peopleNum) {
        ArrayList<BigDecimal> result = new ArrayList<>();
        BigDecimal min = new BigDecimal(1);
        BigDecimal max = total.multiply(new BigDecimal(0.3)).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal average = total.divide(new BigDecimal(peopleNum)).setScale(2, BigDecimal.ROUND_HALF_UP);

        if (total.compareTo(new BigDecimal(peopleNum)) < 0) {
            return null;
        }
        if (total.compareTo(new BigDecimal(peopleNum).multiply(max)) > 0) {
            return null;
        }
        while (peopleNum > 3) {
            //每三个平均值的和 给三个人分。  即：一个max给三个人分（前置条件一定成立）
            BigDecimal averageThreeTimes = average.multiply(new BigDecimal(3)).setScale(2);
            BigDecimal one = min.add((averageThreeTimes.subtract(new BigDecimal(1))).multiply(new BigDecimal(random.nextDouble()))).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal two = min.add((averageThreeTimes.subtract(one)).multiply(new BigDecimal(random.nextDouble()))).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal three = averageThreeTimes.subtract(one).subtract(two);
            result.add(one);
            result.add(two);
            result.add(three);
            peopleNum -= 3;
        }
        if (peopleNum == 1) {
            result.add(average);
        } else if (peopleNum == 2) {
            //剩下的钱数为20%
            BigDecimal averageTwoTimes = average.multiply(new BigDecimal(2)).setScale(2);
            BigDecimal one = min.add((averageTwoTimes.subtract(new BigDecimal(1))).multiply(new BigDecimal(random.nextDouble()))).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal two = averageTwoTimes.subtract(one);
            result.add(one);
            result.add(two);
        }
        return result;
    }
}
