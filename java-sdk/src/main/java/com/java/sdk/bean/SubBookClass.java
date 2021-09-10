package com.java.sdk.bean;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringValueResolver;

/**
 * @author chenfh
 * @date 2021-06-08 16:20
 */
public class SubBookClass extends Book implements BeanClassLoaderAware, EnvironmentAware, EmbeddedValueResolverAware,
        ResourceLoaderAware, ApplicationEventPublisherAware, MessageSourceAware {
    private String bookSystem;

    public SubBookClass() {
        super();
        System.out.println("子类：bean实例化 ");
    }

    public String getBookSystem() {
        return bookSystem;
    }

    public void setBookSystem(String bookSystem) {
        System.out.println("子类：设置属性值");
        this.bookSystem = bookSystem;
    }

    // 实现 BeanClassLoaderAware 的方法

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("子类：调用 BeanClassLoaderAware 的 setBeanClassLoader 方法");
    }

    // 实现 ApplicationEventPublisherAware 的方法
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("子类：调用 ApplicationEventPublisherAware 的 setApplicationEventPublisher 方法");
    }

    // 实现 EmbeddedValueResolverAware 的方法
    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        System.out.println("子类：调用 EmbeddedValueResolverAware 的 setEmbeddedValueResolver 方法");
    }

    // 实现 EnvironmentAware 的方法
    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("子类：调用 EnvironmentAware 的 setEnvironment 方法");
    }
    // 实现 MessageSourceAware 的方法
    @Override
    public void setMessageSource(MessageSource messageSource) {
        System.out.println("子类：调用 MessageSourceAware 的 setMessageSource 方法");
    }
    // 实现 ResourceLoaderAware 的方法
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("子类：调用 ResourceLoaderAware 的 setResourceLoader 方法");
    }
}
