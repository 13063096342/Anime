package com.java.sdk.util;

import com.java.sdk.exception.BusinessException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author chenfh
 * @date 2020-07-24 10:02
 */
public class ValidatorUtil {

    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public static void doValidated(Object object) {
        Set<ConstraintViolation<Object>> validateList = validator.validate(object);
        if (!validateList.isEmpty()) {
            throw new BusinessException("验证失败", validateList.iterator().next().getMessage());
        }

    }

}
