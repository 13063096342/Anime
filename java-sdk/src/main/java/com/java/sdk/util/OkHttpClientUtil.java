package com.java.sdk.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author tzx
 * Created by tzx on 2019/06/03.
 */

@Slf4j
public class OkHttpClientUtil {

    private static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");

    private static final MediaType FORM_TYPE_UTF_8 = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");

    private final static OkHttpClient HTTP_CLIENT;

    private final static OkHttpClient HTTP_CLIENT_SSL;

    static {
        try {
            //SSL  忽略
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            //
            HTTP_CLIENT = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20,TimeUnit.SECONDS)
                    .readTimeout(20,TimeUnit.SECONDS)
                    .build();

            HTTP_CLIENT_SSL = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20,TimeUnit.SECONDS)
                    .readTimeout(20,TimeUnit.SECONDS)
                    .sslSocketFactory(sslSocketFactory, new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    })
                    .build();


        } catch (Exception e) {
            log.error("构建OKHttpClient出问题..{}",e);
            throw new RuntimeException(e);
        }
    }


    public static String post(String url,String json) throws IOException {
        RequestBody body = RequestBody.create(JSON_TYPE,json);
        return postRequest(url,body);
    }

    public static String post_utf8(String url, JSONObject formParams) throws IOException {
        StringBuffer sb = new StringBuffer();
        for (String key: formParams.keySet()) {
            sb.append(key+"="+formParams.get(key)+"&");
        }
        RequestBody body = RequestBody.create(FORM_TYPE_UTF_8, sb.toString());
        return postRequest(url,body);
    }

    public static String post_utf8(String url, Map<String,String> formParams) throws IOException {
        StringBuffer sb = new StringBuffer();
        for (String key: formParams.keySet()) {
            sb.append(key+"="+formParams.get(key)+"&");
        }
        RequestBody body = RequestBody.create(FORM_TYPE_UTF_8, sb.toString());
        return postRequest(url,body);
    }

    public static String post_utf8(String url, String formParamsStr) throws IOException {
        RequestBody body = RequestBody.create(FORM_TYPE_UTF_8, formParamsStr);
        return postRequest(url,body);
    }

    public static String post(String url, Map<String,String> formParams) throws IOException {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String,String> entry : formParams.entrySet()){
            builder.add(entry.getKey(),entry.getValue());
        }
        return postRequest(url,builder.build());
    }

    private static String postRequest(String url,RequestBody body) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        OkHttpClient client = getHttpClient(url);
        try(Response response = client.newCall(request).execute()){
            return response.body().string();
        }
    }

    /**
     * Post请求发送数据 需要自定义请求头
     * @param url
     * @param headerMap
     * @param bodyContent
     * @return
     */
    public static String post(String url,Map<String,String> headerMap,String bodyContent) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), bodyContent);
        if (headerMap == null) {
            log.error("请求参数为空");
            return null;
        }
        Request.Builder builder = new Request.Builder()
                .url(url)
                .post(requestBody);
        for (Map.Entry<String, String> header : headerMap.entrySet()) {
            builder.addHeader(header.getKey(), header.getValue()).build();
        }
        Request request = builder.build();

        OkHttpClient client = getHttpClient(url);
        log.info("okHttp请求{},head:{},body:{}", url, JSONObject.toJSONString(headerMap), bodyContent);
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            log.info("okHttp请求{},result:{}", url, result);
            return result;
        } catch (Exception e) {
            log.error("调用okHttp的newCall方法出错", e);
        }
        return "";
    }

    public static Response get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        OkHttpClient client = getHttpClient(url);
        try(Response response = client.newCall(request).execute()){
            return response;
        }
    }


    private static OkHttpClient getHttpClient(String url){
        if (url.startsWith("https")){
            return HTTP_CLIENT_SSL;
        }
        return HTTP_CLIENT;
    }
}
