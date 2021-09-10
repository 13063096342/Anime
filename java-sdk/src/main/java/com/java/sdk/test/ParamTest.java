package com.java.sdk.test;

import com.java.sdk.model.Person;
import org.springframework.beans.BeanUtils;

/**
 * @author chenfh
 * @date 2021-07-29 10:54
 */
public class ParamTest {
    public static void main(String[] args) {
        Person person = BeanUtils.instantiateClass(Person.class);
        System.out.println(person.getName());
        /*int c = 66;
        String d = "hello";
        Person person = new Person();
        person.setName("Inorly");
        test(c, d);
        test2(person);
        System.out.println("c=" + c + ";d=" + d);
        System.out.println(person.getName());*/
    }

    public static void test(int a, String b) {
        a = 55;
        b = "java";
    }

    public static void test2(Person person) {
        person.setName("mio");
    }
}
