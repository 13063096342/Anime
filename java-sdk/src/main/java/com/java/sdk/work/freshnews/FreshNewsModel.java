package com.java.sdk.work.freshnews;

import lombok.Data;

/**
 * @author chenfh
 * @date 2020-10-26 14:25
 */
@Data
public class FreshNewsModel {
    private String id;
    private Long userId;
    private String code;
    private String subCode;
    private String content;
    private Long fansId;
    private Long time;

}
