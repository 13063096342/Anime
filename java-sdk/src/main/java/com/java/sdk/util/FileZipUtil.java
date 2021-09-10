package com.java.sdk.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author chenfh
 * @date 2021-04-09 17:10
 */
public class FileZipUtil {
    private static void handlerFile(ZipOutputStream zip, File file, String dir) throws Exception {
        //如果当前的是文件夹，则进行进一步处理
        if (file.isDirectory()) {
            //得到文件列表信息
            File[] fileArray = file.listFiles();
            if (fileArray == null) {
                return;
            }
            //将文件夹添加到下一级打包目录
            zip.putNextEntry(new ZipEntry(dir + "/"));
            dir = dir.length() == 0 ? "" : dir + "/";
            //递归将文件夹中的文件打包
            for (File f : fileArray) {
                handlerFile(zip, f, dir + f.getName());
            }
        } else {
            //当前的是文件，打包处理
            //文件输入流
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            ZipEntry entry = new ZipEntry(dir);
            zip.putNextEntry(entry);
            zip.write(FileUtils.readFileToByteArray(file));
            IOUtils.closeQuietly(bis);
            zip.flush();
            zip.closeEntry();
        }
    }

    private static byte[] createZip(String sourceFilePath) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        //将目标文件打包成zip导出
        File file = new File(sourceFilePath);
        handlerFile(zip, file, "");
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }


    public static void exportZip(HttpServletResponse response, String sourceFilePath) {
        //文件名以时间戳作为前缀
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String filePrefix = sdf.format(new Date());
        String downloadName = filePrefix + ".zip";
        //将文件进行打包下载
        try {
            OutputStream out = response.getOutputStream();
            //接收压缩包字节
            byte[] data = createZip(sourceFilePath);
            response.reset();
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Expose-Headers", "*");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + downloadName);
            response.addHeader("Content-Length", "" + data.length);
            response.setContentType("application/octet-stream;charset=UTF-8");
            IOUtils.write(data, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       /* @GetMapping("/exportFile")
        public Result exportFile(HttpServletResponse response) {
            //第二个参数为：要压缩文件的地址
            FileZipUtil.exportZip(response, "/Users/Downloads");
        }*/
    }

}
