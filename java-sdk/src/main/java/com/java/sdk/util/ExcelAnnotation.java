package com.java.sdk.util;

import com.java.sdk.valid.MyValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * @author chenfh
 * @date 2020-08-12 15:54
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyValidator.class)
@Documented
public @interface ExcelAnnotation {
    String exportName() default "";
}
