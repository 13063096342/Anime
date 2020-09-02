package com.java.sdk.model;

import com.alibaba.fastjson.JSONObject;
import lombok.ToString;

/**
 * @author chenfh
 * @date 2020-08-05 13:47
 */
@ToString
public class JsonModel {
    private Integer appId;
    private Integer appid;

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public Integer getAppId() {
        return appId;
    }

    public Integer getAppid() {
        return appid;
    }

    public static void main(String[] args) {
        String str = "{\"appId\":2,\"appid\":234}";

        JSONObject jsonObject = JSONObject.parseObject(str);
        System.out.println("origin string:" + jsonObject.toString());

        JsonModel jsonModel = jsonObject.toJavaObject(JsonModel.class);
        System.out.println("jsonObject.toJavaObject:" + jsonModel.toString());

        JsonModel jsonModel2 = JSONObject.parseObject(str, jsonModel.getClass());
        System.out.println("JSONObject.parseObject:" + jsonModel2.toString());
    }
}
