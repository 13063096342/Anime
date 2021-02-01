package com.java.sdk.dao.model;

/**
 * @author chenfh
 * @date 2020-10-27 09:54
 */
public class FreshNews extends BaseModel{
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 消息分类编码
     */
    private String code;

    /**
     * 消息子类编码
     */
    private String subCode;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 粉丝id（动态发起）
     */
    private Long fansId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getFansId() {
        return fansId;
    }

    public void setFansId(Long fansId) {
        this.fansId = fansId;
    }
}