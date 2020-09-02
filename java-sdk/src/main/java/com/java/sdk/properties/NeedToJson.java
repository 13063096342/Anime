package com.java.sdk.properties;

import java.lang.annotation.*;

/**
 * @author chenfh
 * @date 2020-04-20 17:56
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedToJson {
}
