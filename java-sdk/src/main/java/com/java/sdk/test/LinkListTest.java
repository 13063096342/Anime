package com.java.sdk.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenfh
 * @date 2021-08-02 11:20
 */
public class LinkListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("111");
        list.add("111");
        list.add("111");
        list.add("333");
        list.add("333");
        for (String str : list){
            if("111".equals(str)){
                list.remove(str);//抛出异常java.util.ConcurrentModificationException
            }
        }
    }
}
