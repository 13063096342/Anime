package com.java.sdk.model;

import java.io.Serializable;

/**
 * @author chenfh
 * @date 2020-07-01 16:29
 */
public class ResponseBak implements Serializable {
    private static final long serialVersionUID = -1L;
    protected String code;
    protected String msg;
    protected Object data;

    public ResponseBak() {
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public ResponseBak setCode(String code) {
        this.code = code;
        return this;
    }

    public ResponseBak setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResponseBak setData(Object data) {
        this.data = data;
        return this;
    }
}
