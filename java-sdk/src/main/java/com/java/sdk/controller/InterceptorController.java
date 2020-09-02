package com.java.sdk.controller;

import com.java.sdk.model.Response;
import com.java.sdk.util.ResponseUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenfh
 * @date 2020-08-24 11:43
 */
@RestController
public class InterceptorController {

    @PostMapping("/interceptor/test")
    public Response myVaild(String origin, String newString) {
        System.out.println("origin:" + origin);
        System.out.println("newString:" + newString);
        return ResponseUtil.ok();
    }

}
