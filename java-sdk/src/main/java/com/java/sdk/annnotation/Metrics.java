package com.java.sdk.annnotation;

import java.lang.annotation.*;

/**
 * @author chenfh
 * @date 2020-12-07 11:04
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface Metrics {

    /**
     * 在方法成功后打点，记录方法的执行时间发送到指标系统，默认开启
     */
    boolean recordSuccessMetrices() default true;

    /**
     * 在方法失败后打点，记录方法的执行时间发送到指标系统，默认开启
     */
    boolean recordFailMetrices() default true;

    /**
     * 通过日志记录参数，默认开启
     */
    boolean logParameters() default true;

    /**
     * 通过日志记录返回值，默认开启
     */
    boolean logReturn() default true;

    boolean logException() default true;

    boolean ignoreExcetion() default false;
}
