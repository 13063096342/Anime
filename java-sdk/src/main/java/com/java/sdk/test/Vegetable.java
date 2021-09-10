package com.java.sdk.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;

/**
 * @author chenfh
 * @date 2020-11-09 19:14
 */
@Data
@AllArgsConstructor
public class Vegetable {
    private Integer price;
    private String name;

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            System.out.println( random.nextDouble());

        }
    }
}
