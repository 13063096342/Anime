package com.java.sdk.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.java.sdk.valid.MyVaild;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

/**
 * @author chenfh
 * @date 2020-03-13 14:47
 */

@Data
@Accessors(chain = true)
public class Person {
    @Excel(name = "姓名", orderNum = "0", width = 15)
    @NotNull
    private String name;

    @Excel(name = "年龄", orderNum = "1", width = 15)
    private Integer age;

    @MyVaild(message = "不能叫人sb")
    private String remark;

    private List<String> stringList;

    private Boolean flag;

    private HashMap<String, String> bizArgs;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }
}
