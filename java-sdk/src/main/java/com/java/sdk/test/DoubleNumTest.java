package com.java.sdk.test;

/**
 * @author chenfh
 * @date 2021-07-14 17:52
 */
public class DoubleNumTest {
    public static void main(String[] args) {
        Double num = -12.91;
        boolean negative = false;
        if (num < 0) {
            negative = true;
            num = -num;
        }
        int intValue = num.intValue();
        Double rest = num - num.intValue();
        Double start;
        Double end;

        if (rest < 0.49) {
            start = intValue - 0.01;
            end = intValue + 0.44;
            System.out.println((negative?"-":" ")+(Math.abs(rest - 0.44) > Math.abs(rest + 0.01) ? start.toString() : end.toString()));
        } else {
            start = intValue + 0.99;
            end = intValue + 0.44;
            System.out.println((negative?"-":" ")+(Math.abs(rest - 0.99) > Math.abs(rest - 0.44) ? end.toString() : start.toString()));        }
    }
}
