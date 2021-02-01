package com.java.sdk.work.freshnews;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenfh
 * @date 2020-10-26 14:52
 */
public class FreshNewsFactory implements ApplicationContextAware, InitializingBean {
    private ApplicationContext applicationContext;

    private static Map<String, IFreshNews> freshNewsHashMap = new HashMap<>();


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, IFreshNews> beansOfType = applicationContext.getBeansOfType(IFreshNews.class);
        beansOfType.forEach((key, value) -> freshNewsHashMap.put(value.getSubCode(), value));
    }

    public <T extends IFreshNews> T getFreshNews(String subCode) {
        T iFreshNews = (T) freshNewsHashMap.get(subCode);
        if (null == iFreshNews) {
            throw new RuntimeException("无法处理该消息类型");
        }
        return iFreshNews;
    }


}
