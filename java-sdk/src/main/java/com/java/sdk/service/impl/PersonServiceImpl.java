package com.java.sdk.service.impl;

import com.java.sdk.model.Person;
import com.java.sdk.service.AsyncDService;
import com.java.sdk.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author chenfh
 * @date 2020-05-08 12:07
 */
@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private AsyncDService asyncDService;

    @Override
    public Person getPerson() {
        Person person = new Person();
        person.setAge(11);
        person.setName("Inory");
        try {
            asyncDService.doSomething();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Async
    @Override
    public void doSomething() throws InterruptedException {
        System.out.println("A线程开始执行------");
        Thread.sleep(10000);
        System.out.println("A线程执行结束------"+System.currentTimeMillis());
    }
}
