package com.java.sdk.test;

import com.java.sdk.model.Person;

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
        SonMessage sonMessage = new SonMessage();
        Person person = sonMessage.buildMessage();
        System.out.println(person.getName());
    }
}
