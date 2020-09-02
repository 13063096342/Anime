package com.java.sdk.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelCollectionParams;
import cn.afterturn.easypoi.util.PoiCellUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author chenfh
 * @date 2020-03-13 14:34
 */
public class ExcelUtils {
    /**
     * excel 导出
     *
     * @param list           数据
     * @param title          标题
     * @param sheetName      sheet名称
     * @param pojoClass      pojo类型
     * @param fileName       文件名称
     * @param isCreateHeader 是否创建表头
     * @param response
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
                                   boolean isCreateHeader, HttpServletResponse response) throws IOException {
        ExportParams exportParams = new ExportParams(title, sheetName, ExcelType.XSSF);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);
    }

    /**
     * excel 导出
     *
     * @param list      数据
     * @param title     标题
     * @param sheetName sheet名称
     * @param pojoClass pojo类型
     * @param fileName  文件名称
     * @param response
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
                                   HttpServletResponse response) throws IOException {
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName, ExcelType.XSSF));
    }

    /**
     * excel 导出
     *
     * @param list         数据
     * @param pojoClass    pojo类型
     * @param fileName     文件名称
     * @param response
     * @param exportParams 导出参数
     */
    public static void exportExcel(List<?> list, Class<?> pojoClass, String fileName, ExportParams exportParams,
                                   HttpServletResponse response) throws IOException {
        defaultExport(list, pojoClass, fileName, response, exportParams);
    }

    /**
     * excel 导出
     *
     * @param list     数据
     * @param fileName 文件名称
     * @param response
     */
    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response)
            throws IOException {
        defaultExport(list, fileName, response);
    }

    /**
     * 默认的 excel 导出
     *
     * @param list         数据
     * @param pojoClass    pojo类型
     * @param fileName     文件名称
     * @param response
     * @param exportParams 导出参数
     */
    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response,
                                      ExportParams exportParams) throws IOException {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        downLoadExcel(fileName, response, workbook);
    }


    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response)
            throws IOException {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        downLoadExcel(fileName, response, workbook);
    }

    /**
     * 下载
     *
     * @param fileName 文件名称
     * @param response
     * @param workbook excel数据
     */
    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook)
            throws IOException {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(fileName + "." + ExcelTypeEnum.XLSX.getValue(), "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * excel 导入
     *
     * @param filePath   excel文件路径
     * @param titleRows  标题行
     * @param headerRows 表头行
     * @param pojoClass  pojo类型
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass)
            throws IOException {
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        params.setNeedSave(true);
        params.setSaveUrl("/excel/");
        try {
            return ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new IOException("模板不能为空");
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * excel 导入
     *
     * @param file      excel文件
     * @param pojoClass pojo类型
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(MultipartFile file, Class<T> pojoClass) throws IOException {
        return importExcel(file, 1, 1, pojoClass);
    }

    /**
     * excel 导入
     *
     * @param file       excel文件
     * @param titleRows  标题行
     * @param headerRows 表头行
     * @param pojoClass  pojo类型
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)
            throws IOException {
        return importExcel(file, titleRows, headerRows, false, pojoClass);
    }

    /**
     * excel 导入
     *
     * @param file       上传的文件
     * @param titleRows  标题行
     * @param headerRows 表头行
     * @param needVerfiy 是否检验excel内容
     * @param pojoClass  pojo类型
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, boolean needVerfiy,
                                          Class<T> pojoClass) throws IOException {
        if (file == null) {
            return null;
        }
        try {
            return importExcel(file.getInputStream(), titleRows, headerRows, needVerfiy, pojoClass);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * excel 导入
     *
     * @param inputStream 文件输入流
     * @param titleRows   标题行
     * @param headerRows  表头行
     * @param needVerify  是否检验excel内容
     * @param pojoClass   pojo类型
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(InputStream inputStream, Integer titleRows, Integer headerRows,
                                          boolean needVerify, Class<T> pojoClass) throws IOException {
        if (inputStream == null) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        params.setSaveUrl("/excel/");
        params.setNeedSave(true);
        params.setNeedVerify(needVerify);
        try {
            return ExcelImportUtil.importExcel(inputStream, pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new IOException("excel文件不能为空");
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public static <T> List<T> importExcel(InputStream inputStream, Class<T> pojoClass) throws IOException, IllegalAccessException, InstantiationException {

        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        //默认第一行为表头ui
        Map<Integer, String> titleMap = getTitleMap(sheet.getRow(0));
        Field[] declaredFields = pojoClass.getDeclaredFields();

        HashMap<String, String> fieldMap = new HashMap<>(8);
        for (Field field : declaredFields) {
            ExcelAnnotation annotation = field.getAnnotation(ExcelAnnotation.class);
            if (annotation != null) {
                fieldMap.put(annotation.exportName(), field.getName());
            }
        }

        ArrayList<T> arrayList = new ArrayList<>();
        for (int i = 1; i <= lastRowNum; i++) {
            T t = pojoClass.newInstance();
            for (Map.Entry<String, String> field : fieldMap.entrySet()) {
                String importName = field.getKey();
                for (Map.Entry entry : titleMap.entrySet()) {
                    if (entry.getValue().equals(importName)) {
                        ClassUtils.fillFieldValue(t, field.getValue(), getKeyValue(sheet.getRow(i).getCell((Integer) entry.getKey())));
                        break;
                    }
                }
            }
            arrayList.add(t);
        }

        return arrayList;
    }

    /**
     * Excel 类型枚举
     */
    enum ExcelTypeEnum {
        XLS("xls"), XLSX("xlsx");
        private String value;

        ExcelTypeEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    /**
     * 获取表格字段列名
     */
    private static Map<Integer, String> getTitleMap(Row row) {
        Map<Integer, String> titleMap = new LinkedHashMap<Integer, String>();
        Iterator<Cell> cellTitle;

        cellTitle = row.cellIterator();
        while (cellTitle.hasNext()) {
            Cell cell = cellTitle.next();
            String value = getKeyValue(cell);
            assert value != null;
            value = value.replace("\n", "");
            int i = cell.getColumnIndex();

            if (StringUtils.isNotEmpty(value)) {
                titleMap.put(i, value);
            }
        }
        return titleMap;
    }

    /**
     * 获取key的值,针对不同类型获取不同的值
     *
     * @author JueYue 2013-11-21
     */
    private static String getKeyValue(Cell cell) {
        Object obj = PoiCellUtil.getCellValue(cell);
        return obj == null ? null : obj.toString().trim();
    }

    /**
     * 获取这个名称对应的集合信息
     */
    private ExcelCollectionParams getCollectionParams(List<ExcelCollectionParams> excelCollection,
                                                      String collectionName) {
        for (ExcelCollectionParams excelCollectionParams : excelCollection) {
            if (collectionName.equals(excelCollectionParams.getExcelName())) {
                return excelCollectionParams;
            }
        }
        return null;
    }
}
