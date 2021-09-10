package com.java.sdk.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.java.sdk.valid.MyVaild;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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
        System.out.println("反射构造2");
        this.name = name;
        this.age = age;
    }

    public Person() {
        System.out.println("反射构造");
    }

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        List<Object> list2 = new ArrayList<>(5);
        for (int i = 0; i < list.size(); i++) {
            list2.add(list.get(i));
        }

        System.out.println(list2.get(3));
    }
}
