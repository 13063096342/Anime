package com.java.sdk.service.impl;

import com.java.sdk.model.Person;
import com.java.sdk.service.IValidatedService;
import com.java.sdk.util.SpringUtils;
import com.java.sdk.util.ValidatorUtil;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * @author chenfh
 * @date 2020-04-17 15:47
 */
@Service
@Validated
public class ValidatedServiceImpl implements IValidatedService {

    @Override
    public String testValideted(Person person) {
        System.out.println("testValideted -- Person");
        return "testValideted -- Person";
    }

    @Override
    public void myValideted(@Valid Person person) {
        System.out.println("做校验");
    }

    @Override
    public void methosValidTest(Person person) {
        IValidatedService bean = SpringUtils.getBean(IValidatedService.class);

        bean.myValideted(person);
    }

    @Override
    public void utilValidTest(Person person) {
        ValidatorUtil.doValidated(person);
    }
}
