package com.java.sdk.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author chenfh
 * @date 2021-08-30 20:34
 * 贪心算法
 */
public class AliTest3 {

    public static Random random = new Random();

    public static void main(String[] args) {
        BigDecimal total = new BigDecimal(100);
        Integer peopleNum = 4;
        ArrayList<BigDecimal> result = getResult(total, peopleNum);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    public static ArrayList<BigDecimal> getResult(BigDecimal total, Integer peopleNum) {
        ArrayList<BigDecimal> result = new ArrayList<>();
        BigDecimal min = new BigDecimal(1);
        BigDecimal max = total.multiply(new BigDecimal(0.3)).setScale(2, BigDecimal.ROUND_HALF_UP);
        while (peopleNum > 1) {
            peopleNum--;
            //保证后面的人启码可以分到最小值
            BigDecimal subMaxTract = total.subtract(new BigDecimal(peopleNum));
            BigDecimal canMax = subMaxTract.compareTo(max) > 0 ? max : subMaxTract;
            //保证后面的人分到最大值可以分完这笔钱
            BigDecimal subMinTract = total.subtract(new BigDecimal(peopleNum).multiply(max));
            BigDecimal canMin = subMinTract.compareTo(min) > 0 ? subMinTract : min;

            BigDecimal award = canMax.multiply(new BigDecimal(random.nextDouble())).setScale(2, BigDecimal.ROUND_HALF_UP);
            award = award.compareTo(canMin) < 0 ? canMin : award;
            total = total.subtract(award);
            result.add(award);
        }
        result.add(total);
        return result;
    }
}
