package com.java.sdk.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.java.sdk.model.Response;
import com.java.sdk.model.ResponseBak;
import com.java.sdk.model.UserUcInfo;
import com.java.sdk.model.UserUcInfoResponse;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @author chenfh
 * @date 2021-03-04 17:21
 */
public class DesUtil {
    /**
     * key要8位，不然会报错：java.security.InvalidKeyException: Wrong key size
     */
    static String key = "goodtime";
    /**
     * DES加密
     *
     * @param content 待加密数据
     * @return
     * @throws Exception
     */
    public static String desEncrypt(String content) throws Exception {
        //指定加密算法、加密模式、填充模式
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        //创建加密规则：指定key和加密类型
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "DES");
        //指定加密模式为加密，指定加密规则
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        //调用加密方法
        byte[] result = cipher.doFinal(content.getBytes());
        //用Base64编码
        return new String(Base64.getEncoder().encode(result));
    }

    /**
     * DES解密
     *
     * @param content 待解密数据
     * @return
     * @throws Exception
     */
    public static String desDecrypt(String content) throws Exception {
        //Base64解码
        byte[] result = Base64.getDecoder().decode(content);
        //指定加密算法、加密模式、填充模式
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        //创建加密规则：指定key和加密类型
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "DES");
        //指定加密模式为解密，指定加密规则
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return new String(cipher.doFinal(result));
    }

    public static void main(String[] args) throws Exception {
        //待加密数据
        String content = "3252354444444";
        //String s = desEncrypt(content);
        //加密   mixRApRDjEQ=
        //System.out.println("加密后数据" + s);
        //解密
        //System.out.println("解密后数据"+desDecrypt(s));
        UserUcInfoResponse response = new UserUcInfoResponse();
        response.setCode("00000");
        response.setMsg("成功");
        UserUcInfo userUcInfo = new UserUcInfo();
        userUcInfo.setAccount("1234");
        userUcInfo.setAccType(1);
        userUcInfo.setSource(22);
        userUcInfo.setAppId(2);
        response.setData(userUcInfo);
        String resultStr = JSONObject.toJSONString(response);
        System.out.println("模仿请求返回结果："+resultStr);

        Response response1 = JSONObject.parseObject(resultStr, Response.class);
        System.out.println("模仿请求返回结果response1："+JSONObject.toJSONString(response1));

        UserUcInfo userUcInfo1 = JSONObject.parseObject(response1.getData().toString(), UserUcInfo.class);
        System.out.println("直接解析返回结果的data:"+JSONObject.toJSONString(userUcInfo1));


    }
}
