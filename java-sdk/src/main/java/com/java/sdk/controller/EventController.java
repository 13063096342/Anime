package com.java.sdk.controller;

import com.java.sdk.model.Response;
import com.java.sdk.service.event.UserService;
import com.java.sdk.util.ResponseUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenfh
 * @date 2021-01-25 17:47
 */
@RestController
public class EventController {

    @Autowired
    private UserService service;

    @GetMapping("/event/publish")
    public Response doSomething2(@Param("userName") String userName) {
        service.register(userName);
        return ResponseUtil.ok(System.currentTimeMillis());

    }
}
