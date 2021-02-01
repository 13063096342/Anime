package com.java.sdk.util;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author chenfh
 * @date 2020-10-19 16:28
 */
public class RestTemplateUtil {
    private static class SingletonRestTemplate {
        static final RestTemplate INSTANCE = new RestTemplate();
    }

    private RestTemplateUtil() {
    }


    public static RestTemplate getInstance() {
        return SingletonRestTemplate.INSTANCE;
    }

    /**
     * post 请求
     * @param url 请求路径
     * @param data body数据
     * @param token JWT所需的Token，不需要的可去掉
     * @return
     */
    public static String post(String url, String data, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Encoding", "UTF-8");
        headers.add("Content-Type", "application/json; charset=UTF-8");
        if (token != null) {
            headers.add("Authorization", token);
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(data, headers);
        return RestTemplateUtil.getInstance().postForObject(url, requestEntity, String.class);
    }

    /**
     * get 请求
     * @param url 请求路径
     * @param token JWT所需的Token，不需要的可去掉
     * @return
     */
    public static  String get(String url, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Encoding", "UTF-8");
        headers.add("Content-Type", "application/json; charset=UTF-8");
        if (token != null) {
            headers.add("Authorization", token);
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = RestTemplateUtil.getInstance().exchange(url, HttpMethod.GET, requestEntity, String.class);
        String responseBody = response.getBody();
        return responseBody;
    }

    /**
     * 发送文件请求
     * @param url
     * @param token
     * @return
     */
    public static String file(String url, MultipartFile file, String token) {
        // 生成临时文件
        String tempFilePath = System.getProperty("java.io.tmpdir") + file.getOriginalFilename();
        File tmpFile = new File(tempFilePath);
        // 结果，抛异常就是 null
        String result = null;
        try {
            // 保存为文件
            file.transferTo(tmpFile);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            headers.setContentType(MediaType.parseMediaType("multipart/form-data;charset=UTF-8"));
            if (token != null) {
                headers.add("Authorization", token);
            }
            MultiValueMap<String,Object> param = new LinkedMultiValueMap<>();
            // 把临时文件变成 FileSystemResource
            FileSystemResource resource = new FileSystemResource(tempFilePath);
            param.add("file",resource);
            HttpEntity<MultiValueMap<String,Object>> formEntity = new HttpEntity<>(param,headers);
            result = RestTemplateUtil.getInstance().postForObject(url, formEntity, String.class);
            //删除临时文件文件
            tmpFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
