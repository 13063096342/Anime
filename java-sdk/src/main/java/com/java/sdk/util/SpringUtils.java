package com.java.sdk.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chenfh
 * @date 2020-07-24 09:35
 */
@Component
public class SpringUtils implements ApplicationContextAware, InitializingBean {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> tClass){
        return applicationContext.getBean(tClass);
    }

    public static <T> T getBean(String name, Class<T> type) {
        return applicationContext.getBean(name, type);
    }

    public static List getBeansOfType(Class clazz) {
        Map map = applicationContext.getBeansOfType(clazz);
        return new ArrayList(map.values());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    private void init() {
    }
}
