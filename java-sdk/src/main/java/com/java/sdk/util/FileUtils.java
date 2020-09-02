package com.java.sdk.util;

import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author chenfh
 * @date 2020-08-12 10:52
 */
public class FileUtils {
    public static void downloadResourceFile(HttpServletResponse response, String resourcePath, String downloadFileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(resourcePath);
        InputStream inputStream = new BufferedInputStream(resource.getInputStream());
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        inputStream.close();
        // 清空response
        response.reset();
        // 设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(downloadFileName.getBytes("UTF-8"), "ISO-8859-1"));
        response.addHeader("Content-Length", "" + resource.contentLength());
        response.setContentType("application/octet-stream");
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        outputStream.write(buffer);
        outputStream.flush();
        outputStream.close();
    }
}
