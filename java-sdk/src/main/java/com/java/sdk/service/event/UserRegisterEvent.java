package com.java.sdk.service.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * @author chenfh
 * @date 2021-01-25 17:49
 */
@Data
public class UserRegisterEvent extends ApplicationEvent {

    private String userName;

    public UserRegisterEvent(Object source) {
        super(source);
    }

    public UserRegisterEvent(Object source,String userName) {
        super(source);
        this.userName = userName;
    }
}
