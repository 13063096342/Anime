import com.alibaba.fastjson.JSONObject;
import com.java.sdk.model.Person;

import java.util.HashMap;

/**
 * @author chenfh
 * @date 2020-06-02 10:25
 */
public class ReduceTest {
    public static void main(String[] args) {
       /* Integer reduce = Stream.of(1, 2, 3, 4, 5).reduce(100, (a, b) -> a + b);
        System.out.println(reduce);*/
        Person person = new Person();
        person.setName("Inorly");
        person.setBizArgs(new HashMap<String, String>(2) {
            {
                put("test", "test");
            }
        });
        System.out.println(JSONObject.toJSONString(person));
    }
}
