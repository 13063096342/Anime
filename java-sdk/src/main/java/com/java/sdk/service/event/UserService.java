package com.java.sdk.service.event;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenfh
 * @date 2021-01-25 17:49
 */
@Service
@Slf4j
public class UserService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    @Transactional(rollbackFor = Throwable.class)
    public void register(String username) {
        log.info("[register][执行用户({}) 的注册逻辑]", username);
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this,username));
    }
}
