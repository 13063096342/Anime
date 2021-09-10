package com.java.sdk.algorithm;

/**
 * @author chenfh  牛顿迭代法求平方根 -- 基于泰勒展开式   y=x^2
 * @date 2021-06-15 10:28
 */
public class NiuDunIteration {
    public static void main(String[] args) {
        System.out.println(sqrt(98));
    }

    public static double sqrt(double c) {
        if (c < 0) {
            return 0;
        }
        Double e = 1e-15;
        double x = c;
        double y = (x + c / x) / 2;
        while (Math.abs(x - y) > e) {
            x = y;
            y = (x + c / x) / 2;
        }
        return x;
    }
}
