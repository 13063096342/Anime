import com.alibaba.fastjson.JSONObject;
import com.java.sdk.model.Person;

import java.util.Random;

/**
 * @author chenfh
 * @date 2020-06-02 10:25
 */
public class ReduceTest {
    public static void main(String[] args) {
       /* Integer reduce = Stream.of(1, 2, 3, 4, 5).reduce(100, (a, b) -> a + b);
        System.out.println(reduce);*/
       /* LocalDateTime oneDayBefore = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        Date startTime = LocalDateTimeUtils.convertLocalDateToDate(LocalDateTimeUtils.getDayStart(oneDayBefore));
        Date endTime = LocalDateTimeUtils.convertLocalDateToDate(LocalDateTimeUtils.getDayEnd(oneDayBefore));
        System.out.println(formatter.format(oneDayBefore));
        System.out.println("startTime : "+startTime.getTime());
        System.out.println("endTime : "+endTime.getTime());*/
       Integer age = 34255;
        Person person = new Person("a",age);
        System.out.println("pre person:"+ JSONObject.toJSON(person));
        age = 34253;
        System.out.println("after person:"+JSONObject.toJSON(person));
    }
}
