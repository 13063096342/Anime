package com.java.sdk.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenfh
 * @date 2020-02-26 11:37
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ServicesException extends RuntimeException {
    public String errorCode;
    public String errorMsg;

    public ServicesException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ServicesException(ExceptionCodeEnums exceptionCodeEnums) {
        super(exceptionCodeEnums.getDesc());
        this.errorCode = exceptionCodeEnums.getCode();
        this.errorMsg = exceptionCodeEnums.getDesc();
    }
}
