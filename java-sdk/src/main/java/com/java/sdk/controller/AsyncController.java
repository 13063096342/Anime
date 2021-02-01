package com.java.sdk.controller;

import com.java.sdk.model.Response;
import com.java.sdk.service.*;
import com.java.sdk.service.impl.PersonServiceImpl;
import com.java.sdk.util.ResponseUtil;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author chenfh
 * @date 2020-05-06 11:12
 */
@RestController
public class AsyncController {
    @Autowired
    private AsyncAService asyncAService;

    @Autowired
    private AsyncBService asyncBService;

    @Autowired
    private AsyncCService asyncCService;

    @Autowired
    private AsyncDService asyncDService;

    @Autowired
    private IPersonService service;

    @GetMapping("/async")
    public void doSomething() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();

        CompletableFuture<String> asyncA = asyncAService.doSomething();
        CompletableFuture<String> asyncB = asyncBService.doSomething();
        CompletableFuture<String> asyncC = asyncCService.doSomething();

        // Wait until they are all done
        //join() 的作用：让“主线程”等待“子线程”结束之后才能继续运行
        CompletableFuture.allOf(asyncA, asyncB, asyncC).join();


        float exc = (float) (System.currentTimeMillis() - start) / 1000;
        System.out.println("Elapsed time: " + exc + " seconds");
        System.out.println("--> " + asyncA.get());
        System.out.println("--> " + asyncB.get());
        System.out.println("--> " + asyncC.get());

    }

    @GetMapping("/async2")
    public Response doSomething2() throws InterruptedException, ExecutionException {
        service.getPerson();
        return ResponseUtil.ok(System.currentTimeMillis());

    }
}
