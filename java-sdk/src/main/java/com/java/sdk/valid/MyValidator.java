package com.java.sdk.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author chenfh
 * @date 2020-07-01 15:51
 */
public class MyValidator implements ConstraintValidator<MyVaild, String> {

    @Override
    public void initialize(MyVaild myVaild) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null && !"sb".equals(value);
    }
}
