package com.java.sdk.test;

import com.java.sdk.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenfh
 * @date 2020-10-27 16:19
 */
public abstract class ParentMessage implements IMessage {
    @Override
    public Person buildMessage() {
        Person person = new Person();
        person.setAge(2);
        person.setName(getContent());
        return person;
    }

    @Override
    public String getContent() {
        return "parent";
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        System.out.println(list.size());
        list.removeIf(x -> x.equals("c"));
        System.out.println(list.size());
    }
}
