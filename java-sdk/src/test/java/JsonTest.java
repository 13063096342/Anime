import com.alibaba.fastjson.JSONObject;
import com.java.sdk.model.JsonModel;

import java.io.IOException;

/**
 * @author chenfh
 * @date 2020-08-05 13:49
 */
public class JsonTest {
    public static void main(String[] args) throws IOException {
        String str = "{\"appId\":2,\"appid\":234}";

        JSONObject jsonObject = JSONObject.parseObject(str);
        System.out.println(jsonObject.toString());

        JsonModel jsonModel = jsonObject.toJavaObject(JsonModel.class);
        System.out.println("jsonObject.toJavaObject:" + jsonModel.toString());

        JsonModel userCancelModel = JSONObject.parseObject(str, JsonModel.class);
        System.out.println(userCancelModel.toString());
       /* ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonModel jsonModel = mapper.readValue(str, new TypeReference<JsonModel>() {
        });*/
        System.out.println(jsonModel.toString());
    }
}
