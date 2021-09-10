package com.java.sdk.service;

import com.alibaba.fastjson.JSONObject;
import com.java.sdk.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author chenfh
 * @date 2020-05-06 11:08
 */
@Service
public class AsyncAService {

    @Autowired
    AsyncDService asyncDService;

    //@Async("taskExecutor")
    @Transactional
    public CompletableFuture<String> doSomething() throws InterruptedException {
        System.out.println("A线程开始执行------");
        Thread.sleep(3000);
        System.out.println("A线程执行结束------");
        return CompletableFuture.completedFuture("A线程执行结束");
    }

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        Person person1 = new Person("a",1);
        Person person2 = new Person("b",2);
        list.add(person1);
        list.add(person2);
        System.out.println(JSONObject.toJSONString(list));
    }
}
