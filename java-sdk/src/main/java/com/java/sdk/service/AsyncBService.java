package com.java.sdk.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author chenfh
 * @date 2020-05-06 11:08
 */
@Service
public class AsyncBService {

    @Async("taskExecutor")
    public CompletableFuture<String> doSomething() throws InterruptedException {
        System.out.println("B线程开始执行------");
        Thread.sleep(3000);
        System.out.println("B线程执行结束------");
        return CompletableFuture.completedFuture("B线程执行结束");
    }
}
