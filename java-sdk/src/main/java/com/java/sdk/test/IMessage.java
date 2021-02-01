package com.java.sdk.test;

import com.java.sdk.model.Person;

/**
 * @author chenfh
 * @date 2020-10-27 16:18
 */
public interface IMessage {
    Person buildMessage();

    String getContent();
}
