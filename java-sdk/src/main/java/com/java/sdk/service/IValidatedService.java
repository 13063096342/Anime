package com.java.sdk.service;

import com.java.sdk.model.Person;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author chenfh
 * @date 2020-04-17 15:45
 */
public interface IValidatedService {
    String testValideted(@NotNull Person person);

    void myValideted(@Valid Person person);

    void methosValidTest(Person person);

    void utilValidTest(Person person);

}
