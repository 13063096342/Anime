package com.java.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chenfh
 * @date 2020-08-19 17:21
 */
@Data
public class JsonTestModel  implements Serializable {
    private String cName;

    @JsonProperty("cName")
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }
}
