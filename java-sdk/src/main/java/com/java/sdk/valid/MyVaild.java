package com.java.sdk.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author chenfh
 * @date 2020-07-01 15:46
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyValidator.class)
@Documented
public @interface MyVaild {
    String message() default "org.hibernate.validator.constraints.NotBlank.message";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
