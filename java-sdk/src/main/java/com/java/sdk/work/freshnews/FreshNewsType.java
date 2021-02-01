package com.java.sdk.work.freshnews;

import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * @author chenfh
 * @date 2020-10-26 13:52
 */
@Getter
public enum FreshNewsType {
    /**
     * 消息总类型
     */
    FRIEND_NEWS("1000","好友互动"),
    SYSTEM_NEWS("2000","系统消息"),

    ;
    private String code;
    private String desc;

    FreshNewsType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    FreshNewsType getByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for(FreshNewsType freshNewsType:FreshNewsType.values()) {
            if (freshNewsType.getCode().equals(code)) {
                return freshNewsType;
            }
        }
        return null;
    }
}
