package com.java.sdk.test;

import com.java.sdk.model.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * @author chenfh
 * @date 2020-10-27 16:21
 */
public class SonMessage extends ParentMessage {
    @Override
    public String getContent() {
        return "son";
    }


    public static void main(String[] args) {
        /*SonMessage sonMessage = new SonMessage();
        Person person = sonMessage.buildMessage();
        System.out.println(person.getName());*/
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);
        a.add(6);
        System.out.println("a.size="+a.size());
        List<Integer> b = new ArrayList<>();
        b.add(1);
        b.add(2);
        b.add(3);
        a.removeAll(b);
        System.out.println("a.size="+a.size());
        a.sort(Comparator.comparingInt(Integer::intValue).reversed());
        for (int i = 0; i < a.size(); i++) {
            System.out.println("a[i]="+a.get(i));
        }
    }
}
