import com.java.sdk.util.LocalDateTimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import static org.mockito.Mockito.*;

/**
 * @author chenfh
 * @date 2021-04-19 11:09
 */
@RunWith(MockitoJUnitRunner.class)
public class MockTest {

    @Test
    public void baseTest() {
        //You can mock concrete classes, not only interfaces
        // 你可以mock具体的类型,不仅只是接口
        LinkedList mockedList = mock(LinkedList.class);
        String a = "1";
        //stubbing
        // 测试桩
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        //following prints "first"
        // 输出“first”
        System.out.println(mockedList.get(0));

        //following throws runtime exception
        // 抛出异常
        System.out.println(mockedList.get(1));

        //following prints "null" because get(999) was not stubbed
        // 因为get(999) 没有打桩，因此输出null
        System.out.println(mockedList.get(999));

        //Although it is possible to verify a stubbed invocation, usually it's just redundant
        //If your code cares what get(0) returns then something else breaks (often before even verify() gets executed).
        //If your code doesn't care what get(0) returns then it should not be stubbed. Not convinced? See here.
        // 验证get(0)被调用的次数
        verify(mockedList).get(0);
    }

    @Test
    public void test() {
        BigDecimal a = new BigDecimal("4.50").setScale(0, BigDecimal.ROUND_HALF_UP);
        System.out.println(a.intValue());
    }

    @Test
    public void test2() {
        Integer a = 400;
        Integer b = 400;
        System.out.println(a == b);
    }

    @Test
    public void testTime() {
        Long aLong = transferTime("2021/5/22");
        System.out.println("timeStamp :" + aLong);
        Date date = new Date(aLong);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("time format:" + dateFormat.format(date));
    }

    private Long transferTime(String time) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date parse = dateFormat.parse(time);
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(parse);
            cal1.set(Calendar.HOUR_OF_DAY, 23);
            cal1.set(Calendar.MINUTE, 59);
            cal1.set(Calendar.SECOND, 59);
            return cal1.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    @Test
    public void testTime2() {
        String time = "2021/5/22";
        Long aLong = transferTime2(time);
        System.out.println("timeStamp :" + aLong);
        Date date = new Date(aLong);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("time format:" + dateFormat.format(date));
    }

    private Long transferTime2(String time) {
        if (StringUtils.isEmpty(time)) {
            return 0L;
        }
        try {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyy/MM/dd").parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                    .parseDefaulting(ChronoField.DAY_OF_MONTH, 1).parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .toFormatter();

            LocalDateTime parse = LocalDateTime.parse(time, formatter);
            return LocalDateTimeUtils.getDayEnd(parse).toEpochSecond(ZoneOffset.of("+8"));
        } catch (Exception e) {
            return 0L;
        }
    }
}
