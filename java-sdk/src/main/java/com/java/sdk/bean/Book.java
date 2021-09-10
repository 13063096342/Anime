package com.java.sdk.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author chenfh
 * @date 2021-06-08 15:25
 */
public class Book implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private String bookName;

    public Book(){
        System.out.println("bean无参构造实例化");
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
        System.out.println("设置属性值");
    }

    public String getBookName() {
        return bookName;
    }

    /**
     * 1、实现 BeanNameAware 的方法
     */
    @Override
    public void setBeanName(String name) {
        System.out.println("调用 BeanNameAware 的 setBeanName 方法");
    }

    /**
     * 2、实现 BeanFactoryAware 的方法
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("调用 BeanFactoryAware 的 setBeanFactory 方法");
    }

    /**
     * 3、实现 ApplicationContextAware 的方法
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("调用 ApplicationContextAware 的 setApplicationContext 方法");
    }

    /**
     * 自定义初始化方法
     */
    @PostConstruct
    public void springPostConstruct() {
        System.out.println("@PostConstruct");
    }

    /**
     *  实现 InitializingBean 的方法
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("调用 InitializingBean 的 afterPropertiesSet 方法");
    }

    // destroy-method
    public void myPreDestory() {
        System.out.println("调用 destroy-method 属性配置的销毁方法");
        System.out.println("---------------destroy-----------------");
    }



    // init-method
    public void myPostConstruct() {
        System.out.println("调用 init-method 属性配置的初始化方法");
    }

    // 自定义销毁方法
    @PreDestroy
    public void springPreDestory() {
        System.out.println("@PreDestory");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("------inside finalize-----");
    }

    /**
     *   实现 DisposableBean
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("调用 DisposableBean 的 destroy 方法");
    }
}

