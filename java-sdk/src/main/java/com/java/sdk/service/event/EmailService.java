package com.java.sdk.service.event;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author chenfh
 * @date 2021-01-25 17:53
 */
@Service
@Slf4j
    public class EmailService implements ApplicationListener<UserRegisterEvent> {
    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        TransmittableThreadLocal transmittableThreadLocal = new TransmittableThreadLocal();
        log.info("[onApplicationEvent][给用户({}) 发送邮件]", event.getUserName());
    }
}
