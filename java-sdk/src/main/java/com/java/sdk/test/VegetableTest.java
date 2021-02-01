package com.java.sdk.test;

import com.java.sdk.util.RandomUtil;

import java.util.*;

/**
 * @author chenfh
 * @date 2020-11-09 18:17
 */
public class VegetableTest {
    public static void main(String[] args) {
        List<Vegetable> list = Arrays.asList(
                new Vegetable(5, "炒生菜"), new Vegetable(12, "花菜炒肉"),
                new Vegetable(8, "韭黄鸡蛋"), new Vegetable(6, "西红柿炒蛋"),
                new Vegetable(20, "炒河粉"),
                new Vegetable(15, "咸饭"), new Vegetable(5, "红烧豆腐"),
                new Vegetable(12, "蒜苔炒肉"),new Vegetable(20, "宫爆鸡丁"),
                new Vegetable(12, "小白菜豆干炒肉"), new Vegetable(30, "煎牛排"),
                new Vegetable(30, "萝卜排骨玉米汤"), new Vegetable(30, "可乐鸡翅"),
                new Vegetable(12, "胡萝卜炒肉"), new Vegetable(10, "玉米烙"),
                new Vegetable(12, "手撕包菜"), new Vegetable(10, "大白菜炒肉"),
                new Vegetable(15, "木须肉"),new Vegetable(8, "醋溜大白菜"),
                new Vegetable(10, "洋葱炒肉"), new Vegetable(6, "洋葱炒蛋"),
                new Vegetable(30, "白灼虾"), new Vegetable(4, "荷包蛋"),
                new Vegetable(15, "海蛎煎"),new Vegetable(12, "黄瓜炒肉"),
                new Vegetable(4, "炒空心菜"), new Vegetable(4, "炒大白菜"),
                new Vegetable(20, "糖醋里脊"), new Vegetable(5, "炒豆芽"),
                new Vegetable(10, "腐竹炒肉"), new Vegetable(10, "豆泡炒肉"),
                new Vegetable(4, "韭菜炒蛋"),new Vegetable(12, "蘑菇炒肉"),
                new Vegetable(4, "上海青")
        );

        int priceTotal = 0;
        int num = 0;
        Set<Integer> container = new HashSet<>();
        while (container.size() <= 4) {
            int i = RandomUtil.nextInt(list.size());
            container.add(i);
        }

        Iterator<Integer> iterator = container.iterator();
        while (iterator.hasNext()) {
            Vegetable vegetable = list.get(iterator.next());
            priceTotal += vegetable.getPrice();
            num++;
            System.out.println("第" + num + "道菜，" + vegetable.getName() + ";价格：" + vegetable.getPrice());
        }
        System.out.println("总计：" + priceTotal + "元");
    }
}
