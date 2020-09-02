package com.java.sdk.properties;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenfh
 * @date 2020-04-20 17:54
 */
public class SelfMethodArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 解析Content-Type为application/x-www-form-urlencoded的默认解析器是ServletModelAttributeMethodProcessor
     */
    private ServletModelAttributeMethodProcessor servletModelAttributeMethodProcessor;

    /**
     * 解析Content-Type为application/json的默认解析器是RequestResponseBodyMethodProcessor
     */
    private RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor;

    final String applicationXwwFormUrlencoded = "application/x-www-form-urlencoded";

    public SelfMethodArgumentResolver(ServletModelAttributeMethodProcessor servletModelAttributeMethodProcessor, RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor) {
        this.servletModelAttributeMethodProcessor = servletModelAttributeMethodProcessor;
        this.requestResponseBodyMethodProcessor = requestResponseBodyMethodProcessor;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestBody.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (webRequest == null) {
            throw new RuntimeException("request is not null");
        }
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        String contentType = servletRequest.getContentType();
        if (contentType.equals(applicationXwwFormUrlencoded)) {
            return servletModelAttributeMethodProcessor.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
        }
        return requestResponseBodyMethodProcessor.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
    }

}
