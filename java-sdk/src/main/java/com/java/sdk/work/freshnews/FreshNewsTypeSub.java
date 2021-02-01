package com.java.sdk.work.freshnews;

import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * @author chenfh
 * @date 2020-10-26 13:52
 */
@Getter
public enum FreshNewsTypeSub {
    /**
     * 消息子类型  1xxx为好友互动，2xxx为系统消息 {@link FreshNewsType}
     */
    ATTENTION_NEWS("1001","有人关注了我"),
    STEAL_COINS_NEWS("1002","有人偷取了我的驾驶币"),
    DONATE_COINS_NEWS("1003","有人送了我驾驶币"),
    GIVE_LIKE_NEWS("1004","有人赞了我的成就"),

    GET_MEDAL_NEWS("2001","获得勋章"),
    BREAK_RECORD_NEWS("2002","打破纪录"),
    OFFICIAL_PK_BREAK_NEWS("2003","官方PK群排行突破"),
    SINGLE_PK_PROCESS_NEWS("2004","单人PK过程变化"),

    ;
    private String code;
    private String desc;

    FreshNewsTypeSub(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    FreshNewsTypeSub getByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for(FreshNewsTypeSub freshNewsTypeSub: FreshNewsTypeSub.values()) {
            if (freshNewsTypeSub.getCode().equals(code)) {
                return freshNewsTypeSub;
            }
        }
        return null;
    }
}
