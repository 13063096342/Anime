package com.java.sdk.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel读写工具类
 */
public class ExcelUtil {
    private final static Logger LOG = LoggerFactory.getLogger(ExcelUtils.class);
    private final static String excel2003L = ".xls";    //2003- 版本的excel
    private final static String excel2007U = ".xlsx";   //2007+ 版本的excel

    public final static String EMPTY_CELL_CONTENT_FLAG = "xo";

    /**
     * 导指标
     *
     * @param in
     * @param fileName
     * @return
     * @throws Exception
     */
    public static List<Map> getByExcel(InputStream in, String fileName) throws Exception {
        List<Map> list = null;


        //创建Excel工作薄
        Workbook work = getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        list = new ArrayList<Map>();
        //遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            //遍历当前sheet中的所有行
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                // 跳过表头
                if (row == null || j == 0) {
                    continue;
                }

                //遍历所有的列
//                List<Object> li = new ArrayList<Object>();
                Map map = new HashMap<>();
                map.put("idx", j + 1);
                // 从第0个cell开始遍历
                for (int k = 0; k < row.getLastCellNum(); k++) {
                    cell = row.getCell(k);
                    String cellValue = getCellValueByCell(cell);
                    if (StringUtils.isEmpty(cellValue)) {
                        map.put(k, "xo");
                    } else {
                        map.put(k, cell);
                    }
                }
                list.add(map);
            }
        }
//        System.out.println(list);
        return list;

    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     *
     * @param inStr,fileName
     * @return
     * @throws Exception
     */
    public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (excel2003L.equals(fileType)) {
            wb = new HSSFWorkbook(inStr);  //2003-
        } else if (excel2007U.equals(fileType)) {
            wb = new XSSFWorkbook(inStr);  //2007+
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }



    /**
     * 获取单元格各类型值，返回字符串类型
     */
    public static String getCellValueByCell(Cell cell) {
        DateFormat df = new SimpleDateFormat();
        //判断是否为null或空串
        if (cell == null || cell.toString().trim().equals("")) {
            return "";
        }
        String cellValue = "";
        CellType cellType = cell.getCellTypeEnum();
        //字符串类型
        if (CellType.STRING.equals(cellType)) {
            cellValue = cell.getStringCellValue().trim();
            cellValue = StringUtils.isEmpty(cellValue) ? "" : cellValue;
            return cellValue;
        }
        // 布尔值
        if (CellType.BOOLEAN.equals(cellType)) {
            cellValue = String.valueOf(cell.getBooleanCellValue());
            return cellValue;
        }
        // 数字
       if (CellType.NUMERIC.equals(cellType)) {
            return cellValue;
        }

        return cellValue;
    }
}
