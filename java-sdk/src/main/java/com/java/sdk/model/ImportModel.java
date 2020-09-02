package com.java.sdk.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.handler.inter.IExcelDataModel;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author chenfh
 * @date 2020-03-13 14:47
 */

@Data
public class ImportModel implements IExcelDataModel, IExcelModel, Serializable {
    private int rowNum;

    private String errorMsg;

    //@ExcelAnnotation(exportName = "version")
    @Excel(name = "version")
    @NotNull(message = "not null")
    private String version;

    //@ExcelAnnotation(exportName = "mobile")
    @Excel(name = "mobile")
    private String mobile;

    //@ExcelAnnotation(exportName = "userId")
    @Excel(name = "userId")
    private String userId;

    //@ExcelAnnotation(exportName = "unionId")
    @Excel(name = "unionId")
    private String unionId;

    //@ExcelAnnotation(exportName = "activity")
    @Excel(name = "activity")
    private String activity;
}
