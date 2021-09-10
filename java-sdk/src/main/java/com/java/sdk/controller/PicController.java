package com.java.sdk.controller;

import com.java.sdk.service.event.UserService;
import com.java.sdk.util.FileZipUtil;
import com.java.sdk.util.OkHttpClientUtil;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenfh
 * @date 2020-03-13 14:31
 */
@RestController
public class PicController {
    @Autowired
    private UserService service;

    @GetMapping("export/pic")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FileZipUtil.exportZip(response, "C:\\Users\\yamei\\Desktop\\picture\\picturetemp_download");
    }

    @GetMapping("create/doc")
    public void createDoc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> phoneList = new ArrayList<>();
        phoneList.add("1234");
        phoneList.add("12345");
        phoneList.add("123456");

        String baseUrl = "/baseDoc";

        File f = new File(baseUrl);
        if (!f.exists()) {
            f.mkdirs();
        }
        phoneList.forEach(x -> {
            String tempName = baseUrl + "/" + x;
            File temp = new File(tempName);
            if (!temp.exists()) {
                temp.mkdirs();
            }
            for (int i = 1; i < 2; i++) {
                try {
                    String tempTxt = tempName + "/" + x + i + ".jpg";

                    Response response1 = OkHttpClientUtil.get("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3355464299,584008140&fm=26&gp=0.jpg");
                    InputStream  in = response1.body().byteStream();
                    //本地图片导出zip
                    //InputStream in = new FileInputStream("C:\\Users\\yamei\\Desktop\\picture\\" + i + ".jpg");
                    OutputStream out = new FileOutputStream(tempTxt);
                    byte[] b = new byte[1024];
                    while ((in.read(b)) != -1) {
                        out.write(b);
                    }
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        FileZipUtil.exportZip(response, baseUrl);

        deleteFolder(f);
        System.out.println("createDoc:" + f.getAbsolutePath());
    }

    public static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }

    @GetMapping("create/test")
    public void createTest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fileName = "/baseDoc/test.jpg";
        InputStream is = null;
        FileOutputStream fos = null;
        String picUrl = "http://yamei-test-oss.iauto360.cn/app-site_4/12/app-site-avatar_20201222_e6e5671c-fc42-4f3e-b4d6-069945048920.jpg";
        try {
            // 构造URL
            URL url = new URL(picUrl);
            // 打开连接
            URLConnection con = url.openConnection();
            //设置请求超时为5s
            con.setConnectTimeout(5*1000);
            // 输入流
            is = con.getInputStream();
            byte[] buf = new byte[2048];
            int len = 0;
            fos = new FileOutputStream(fileName);
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
        } catch (IOException e) {
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
            }
        }
    }

    @GetMapping("create/test2")
    public void createTest2(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fileName = "/baseDoc/test.jpg";
        String url = "https://img3.doubanio.com/view/status/l/public/103861781-665301078817f01.jpg";
        //创建restTemplate对象
        org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
        /**
         * 请求地址
         * 请求方式
         * 请求实体类
         * 请求返回类型
         */
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, byte[].class);
        //获取entity中的数据
        byte[] body = responseEntity.getBody();
        //创建输出流  输出到本地
        FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
        fileOutputStream.write(body);
        //关闭流
        fileOutputStream.close();
    }

}
