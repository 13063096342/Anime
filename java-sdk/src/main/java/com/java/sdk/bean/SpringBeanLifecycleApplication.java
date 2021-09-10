package com.java.sdk.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author chenfh
 * @date 2021-06-08 15:35
 */
public class SpringBeanLifecycleApplication {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-lifecycle.xml");
        Book book = (Book) context.getBean("book");
        System.out.println("开始使用bean：book.bookName = " + book.getBookName());
        ((ClassPathXmlApplicationContext) context).destroy();

        System.out.println("\r\n");
        // 完整的加载过程，当然了解的越多越好
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("sub-bean-lifecycle.xml");
        SubBookClass subBookClass = (SubBookClass) applicationContext.getBean("subBook");
        System.out.println("BookSystemName = " + subBookClass.getBookSystem());
        ((ClassPathXmlApplicationContext) applicationContext).registerShutdownHook();
    }
}
