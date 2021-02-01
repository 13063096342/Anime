package com.java.sdk.service;

import com.java.sdk.model.Person;

/**
 * @author chenfh
 * @date 2020-05-08 12:07
 */
public interface IPersonService {

    Person getPerson();

    void doSomething() throws InterruptedException;
}
