package com.java.sdk.util;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author panyh
 */
public class ExcelSheet {
    public static int SHEET_SIZE = 10000;
    private Sheet sheet;
    private Workbook workbook;
    private CellStyle style;

    Sheet getSheet() {
        return sheet;
    }
    void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }
    public Workbook getWorkbook() {
        return workbook;
    }
    void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }
    CellStyle getStyle() {
        return style;
    }
    void setStyle(CellStyle style) {
        this.style = style;
    }
}
