package com.java.sdk.controller;

import com.alibaba.fastjson.JSONObject;
import com.java.sdk.model.Person;
import com.java.sdk.model.Response;
import com.java.sdk.service.IValidatedService;
import com.java.sdk.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenfh
 * @date 2020-04-17 15:41
 */
@RestController
public class ValidatedController {
    @Autowired
    private IValidatedService validatedService;

   /* @Autowired
    private ExampleService exampleService;*/

    @PostMapping("/valid")
    public void getExcel(@RequestBody Person person) {
        System.out.println("getParam:" + JSONObject.toJSONString(person));
        validatedService.myValideted(person);
        /*System.out.println(serviceProperty.getPort());
        System.out.println(serviceProperty.getContextPath());*/
    }

    @PostMapping("/my-valid")
    public Response myVaild(@RequestBody Person person) {
        validatedService.methosValidTest(person);
        return ResponseUtil.ok();
    }

    @PostMapping("/my-util-valid")
    public Response utilValid(@RequestBody Person person) {
        validatedService.utilValidTest(person);

        return ResponseUtil.ok();
    }

}
