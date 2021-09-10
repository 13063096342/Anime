package com.java.sdk.service;

import com.java.sdk.util.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.concurrent.CompletableFuture;

/**
 * @author chenfh
 * @date 2020-05-06 11:08
 */
@Service
public class AsyncDService {

    @Autowired
    private AsyncAService asyncAService;

    public void doSomething() throws InterruptedException {
        AsyncDService bean = SpringUtils.getBean(AsyncDService.class);
        bean.doSomething2();
    }

    //@Async
    public void doSomething2() throws InterruptedException {
        System.out.println("A线程开始执行------");
        Thread.sleep(10000);
        System.out.println("A线程执行结束------"+System.currentTimeMillis());
    }
}
