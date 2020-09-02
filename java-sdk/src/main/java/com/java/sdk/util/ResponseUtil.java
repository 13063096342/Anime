package com.java.sdk.util;

import com.java.sdk.model.Response;

/**
 * @author chenfh
 * @date 2020-07-01 16:34
 */
public class ResponseUtil {
    /**
     * 返回错误
     *
     * @param data
     * @return
     */
    public static <T> Response ok(T data) {
        return (new Response()).setCode("200").setMsg("成功").setData(data);
    }

    /**
     * 返回错误
     *
     * @param code
     * @param msg
     * @return
     */
    public static Response error(String code, String msg) {
        return (new Response()).setCode(code).setMsg(msg);
    }

    public static <T> Response<T> ok() {
        return (new Response()).setCode("200").setMsg("成功");
    }
}
