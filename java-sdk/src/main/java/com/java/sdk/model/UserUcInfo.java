package com.java.sdk.model;

import lombok.Data;

/**
 * @author chenfh
 * @date 2021-03-05 16:01
 */
@Data
public class UserUcInfo {

    /**
     * 统一用户id
     */
    private Long unionId;

    /**
     * 账号类型 0 手机号
     */
    private Integer accType;

    /**
     * 应用id 2 车智汇 8 车智会
     */
    private Integer appId;

    /**
     * 用户userId
     */
    private Long userId;

    /**
     * 账号
     */
    private String account;

    /**
     * 所属用户系统
     */
    private Integer userSystem;

    /**
     * 注册来源， 22=盐城拉新活动H5
     */
    private Integer source;

    /**
     * 蓝色userId
     */
    private Long bUserId;

    /**
     * 橙色userId
     */
    private Long oUserId;
}
