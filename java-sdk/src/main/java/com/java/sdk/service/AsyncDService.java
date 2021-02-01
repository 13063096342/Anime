package com.java.sdk.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author chenfh
 * @date 2020-05-06 11:08
 */
@Service
public class AsyncDService {

    @Async
    public void doSomething() throws InterruptedException {
        System.out.println("A线程开始执行------");
        Thread.sleep(10000);
        System.out.println("A线程执行结束------"+System.currentTimeMillis());
    }
}
