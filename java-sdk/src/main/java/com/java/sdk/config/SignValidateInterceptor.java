package com.java.sdk.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

/**
 * @author: LiGuo
 * @date: 2018-07-21
 * @since: 1.4.0
 * @note: 请求合法性校验
 */
@Component
@Slf4j
public class SignValidateInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handler1 = (HandlerMethod) handler;
        MethodParameter[] methodParameters = handler1.getMethodParameters();
        Class<?> parameterType = methodParameters[1].getParameterType();
        Object o = parameterType.newInstance();


        response.setCharacterEncoding(Charset.defaultCharset().name());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            ex) throws Exception {

    }

}

