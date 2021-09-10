package com.java.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.java.sdk.enums.FlagEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

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

    public static void main(String[] args) {
        /*String a = "100";
        BigDecimal b = new BigDecimal(a).setScale(2,BigDecimal.ROUND_HALF_DOWN);
        if (b.compareTo(new BigDecimal(100)) >= 0) {
            System.out.println("大于100");
        }

        FlagEnum disable = FlagEnum.DISABLE;
        System.out.println("这是真的");

        Long now = System.currentTimeMillis() / 1000;
        System.out.println("long + :"+now);
        System.out.println("int + :" + now.intValue());
        System.out.println("(int) : "+ (int) (System.currentTimeMillis() / 1000));*/
        System.out.println(toLocalDate(1617865017L));

    }

    public static LocalDate toLocalDate(long timeMillis) {
        Instant queryTime = Instant.ofEpochSecond(timeMillis);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(queryTime, ZoneId.systemDefault());
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;
    }
}
