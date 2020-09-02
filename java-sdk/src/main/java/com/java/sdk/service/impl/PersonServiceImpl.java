package com.java.sdk.service.impl;

import com.java.sdk.model.Person;
import com.java.sdk.service.IPersonService;
import org.springframework.stereotype.Service;

/**
 * @author chenfh
 * @date 2020-05-08 12:07
 */
@Service
public class PersonServiceImpl implements IPersonService {
    @Override
    public Person getPerson() {
        Person person = new Person();
        person.setAge(11);
        person.setName("Inory");
        return person;
    }
}
