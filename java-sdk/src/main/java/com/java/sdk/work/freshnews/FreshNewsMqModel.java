package com.java.sdk.work.freshnews;

import lombok.Data;

/**
 * @author chenfh
 * @date 2020-10-26 15:39
 */
@Data
public class FreshNewsMqModel {
    private String code;
    private String subCode;
    private Long userId;
    private ExtendValue extend;

    @Data
    public class ExtendValue {
        private String content;
        private Long fansId;
    }
}
