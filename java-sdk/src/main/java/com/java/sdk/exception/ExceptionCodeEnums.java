package com.java.sdk.exception;

import lombok.Getter;

/**
 * @author chenfh
 * @date 2020-10-26 18:03
 */
@Getter
public enum ExceptionCodeEnums {
    /**
     *  系统错误信息集合
     */
    DB_DELETE_ERROR("10000","数据删除失败"),
    DB_UPDATE_ERROR("10001","数据更新失败"),
    DB_INSERT_ERROR("10002","数据新增失败"),
    DB_SELECT_ERROR("10003","数据查询失败"),



    REFLECT_ERROR("11000","反射失败"),


    REPEAT_ATTENTION("20001","同一个人一天内只有首次关注会产生动态"),
    REPEAT_STEAL_COINS("20002","同一个人一小时内多次偷取只产生一条动态"),
    REPEAT_DONATE_COINS("20003","同一个人一小时内多次赠送只产生一条动态"),
    REPEAT_OFFICIAL_PK_NEWS("20004","上周官方地区PK结果每周一早上8点产生，只推送记录一次"),
    FANS_ID_NULL("20005","粉丝id不能为空"),
    FANS_NAME_NULL("20006","粉丝名字不能为空"),
    NEWS_SUB_CODE_ERROR("20007","无法处理的消息子类型"),
    NEWS_CODE_NOT_MATCH_ERROR("20008","消息类型不匹配"),
    OFFICIAL_PK_BREAK_NEWS_ERROR("20009","上周官方地区PK结果每周一早上8点产生"),
    PK_HAS_NULL_ERROR("20010","PK对象不能为空"),

    ;
    private String code;
    private String desc;

    ExceptionCodeEnums(String code,String desc) {
        this.code = code;
        this.desc = desc;
    }
}
