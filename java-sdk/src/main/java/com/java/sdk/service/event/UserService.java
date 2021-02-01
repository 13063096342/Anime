package com.java.sdk.service.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @author chenfh
 * @date 2021-01-25 17:49
 */
@Service
@Slf4j
public class UserService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void register(String username) {
        log.info("[register][执行用户({}) 的注册逻辑]", username);
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this,username));
    }
}
