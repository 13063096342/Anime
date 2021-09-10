package com.jack;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenfh
 * @date 2021-08-12 09:29
 */
@Data
public class RpcRequest implements Serializable {

    private String className;

    private String methodName;

    private Object[] args;

}
