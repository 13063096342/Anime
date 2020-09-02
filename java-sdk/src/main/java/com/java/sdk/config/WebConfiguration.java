package com.java.sdk.config;

import com.java.sdk.properties.SelfMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenfh
 * @date 2020-04-21 10:55
 */
@EnableWebMvc
@Configuration
public class WebConfiguration extends DelegatingWebMvcConfiguration {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    private RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor = null;
    private ServletModelAttributeMethodProcessor servletModelAttributeMethodProcessor = null;

    /**
     * 注册自定义注解器
     */
    @PostConstruct
    private void addMethodArgumentResolves() {
        List<HandlerMethodArgumentResolver> argumentResolvers = requestMappingHandlerAdapter.getArgumentResolvers();
        List<HandlerMethodArgumentResolver> myArgumentResolvers = new ArrayList<>(argumentResolvers.size() + 1);
        myArgumentResolvers.add(getSelfMethodArgumentResolver(argumentResolvers));
        myArgumentResolvers.addAll(argumentResolvers);
        requestMappingHandlerAdapter.setArgumentResolvers(myArgumentResolvers);
    }

    SelfMethodArgumentResolver getSelfMethodArgumentResolver(List<HandlerMethodArgumentResolver> handlerMethodArgumentResolvers) {
        if (handlerMethodArgumentResolvers == null) {
            throw new RuntimeException("argumentResolverList must not be null!");
        }
        for (HandlerMethodArgumentResolver item : handlerMethodArgumentResolvers) {
            if (item instanceof ServletModelAttributeMethodProcessor) {
                servletModelAttributeMethodProcessor = (ServletModelAttributeMethodProcessor) item;
            } else if (item instanceof RequestResponseBodyMethodProcessor) {
                requestResponseBodyMethodProcessor = (RequestResponseBodyMethodProcessor) item;
            }
            if (requestResponseBodyMethodProcessor != null && servletModelAttributeMethodProcessor != null) {
                break;
            }
        }
        if (requestResponseBodyMethodProcessor == null || servletModelAttributeMethodProcessor == null) {
            throw new RuntimeException("requestResponseBodyMethodProcessor == null || servletModelAttributeMethodProcessor == null");
        }
        return new SelfMethodArgumentResolver(servletModelAttributeMethodProcessor, requestResponseBodyMethodProcessor);
    }


}

