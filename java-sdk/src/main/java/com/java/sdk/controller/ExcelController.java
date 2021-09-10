package com.java.sdk.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.alibaba.fastjson.JSONObject;
import com.java.sdk.model.ImportModel;
import com.java.sdk.model.JsonTestModel;
import com.java.sdk.model.Person;
import com.java.sdk.model.Response;
import com.java.sdk.service.event.UserService;
import com.java.sdk.util.ExcelUtils;
import com.java.sdk.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenfh
 * @date 2020-03-13 14:31
 */
@RestController
public class ExcelController {
    @Autowired
    private UserService service;

    @GetMapping("export/excel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Person person = new Person();
            person.setName("张三" + i);
            person.setAge(i + 15);
            personList.add(person);
        }
        ExcelUtils.exportExcel(personList, null, "员工信息sheet", Person.class, "员工信息表", response);
    }

    @PostMapping("import/excel")
    public void importExcel(MultipartHttpServletRequest request) throws Exception {

        MultipartFile file1 = request.getFile("file");

        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        params.setNeedVerify(false);

        List<ImportModel> list = ExcelImportUtil.importExcel(file1.getInputStream(), ImportModel.class, params);


        ExcelImportResult<ImportModel> objectExcelImportResult = ExcelImportUtil.importExcelMore(file1.getInputStream(), ImportModel.class, params);
        List<ImportModel> failList = objectExcelImportResult.getFailList();

        for (ImportModel model : failList) {
            model.getRowNum();
            model.getErrorMsg();
        }

        System.out.println(JSONObject.toJSONString(list));

        //List<ImportModel> byExcel = ExcelUtils.importExcel(file1.getInputStream(), ImportModel.class);

    }

    @PostMapping("download/excel")
    public Response downExcel(HttpServletResponse response) throws IOException {
        //FileUtil.downloadResourceFile(response, "/低版本用户.xlsx", "低版本用户.xlsx");

        JsonTestModel model = new JsonTestModel();
        model.setcName("2345");
        return ResponseUtil.ok(model);
    }

    public static void main(String[] args) {
        System.out.println(Math.pow(1.1, 8) * 11);
    }
}
