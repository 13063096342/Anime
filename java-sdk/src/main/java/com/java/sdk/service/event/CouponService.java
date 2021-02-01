package com.java.sdk.service.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author chenfh
 * @date 2021-01-25 17:54
 */
@Service
@Slf4j
public class CouponService {
    @EventListener
    public void addCoupon(UserRegisterEvent event) {
        log.info("[addCoupon][给用户({}) 发放优惠劵]", event.getUserName());
    }
}
