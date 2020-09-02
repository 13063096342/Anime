package com.java.sdk.exception;

/**
 * @author chenfh
 * @date 2020-09-02 17:36
 */
public class BusinessException extends RuntimeException{
    protected String code;
    protected Object[] args;
    protected String msg;
    protected Object data;

    public BusinessException(String code) {
        this(code, (Object[])null, (String)null, (Throwable)null);
    }

    public BusinessException(String code, Throwable cause) {
        this(code, (Object[])null, (String)null, cause);
    }

    public BusinessException(String code, String msg) {
        this(code, (Object[])null, msg, (Throwable)null);
    }

    public BusinessException(String code, String msg, Throwable cause) {
        this(code, (Object[])null, msg, cause);
    }

    public BusinessException(String code, Object[] args) {
        this(code, args, (String)null, (Throwable)null);
    }

    public BusinessException(String code, Object[] args, Throwable cause) {
        this(code, args, (String)null, cause);
    }

    public BusinessException(String code, Object[] args, String msg) {
        this(code, args, msg, (Throwable)null);
    }

    public BusinessException(String code, Object[] args, String msg, Throwable cause) {
        this(code, args, msg, (Object)null, cause);
    }

    public BusinessException(String code, Object[] args, String msg, Object data, Throwable cause) {
        super(cause);
        this.code = code;
        this.args = args;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return this.code;
    }

    public Object[] getArgs() {
        return this.args;
    }

    public String getMsg() {
        return this.msg;
    }

    public Object getData() {
        return this.data;
    }
}
