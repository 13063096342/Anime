package com.java.sdk.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author chenfh
 * @date 2020-12-07 11:16
 */
@Aspect
@Component
@Slf4j
public class MetricsAspect {

    /**
     * 让Spring帮助注入ObjectMapper,方便通过JSON序列化记录方法出参和入参
     */
    @Autowired
    private ObjectMapper objectMapper;

    private static final Map<Class<?>, Object> DEFAULT_VALUES = Stream.of(boolean.class, byte.class, int.class, long.class,
            short.class, double.class, float.class, char.class).collect(Collectors.toMap(x -> (Class<?>) x, x -> Array.get(Array.newInstance(x, 1), 0)));

    public static <T> T getDefaultValue(Class<T> tClass) {
        return (T)DEFAULT_VALUES.get(tClass);
    }


}
