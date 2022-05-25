package com.cc.cv;


import com.zyq.utils.excel.ExcelImport;
import lombok.Data;

@Data
public class User {
    /**
     * 通过excelUtils类进行规则定义
     */

    private int rowNum;

    @ExcelImport(value = "姓名", required = true, unique = true)
    private String name;

    @ExcelImport("年龄")
    private String age;

    @ExcelImport(value = "性别", kv = "1-男;2-女")
    private String sex;

    @ExcelImport(value = "电话", maxLength = 11, required = true)
    private String tel;

    @ExcelImport("城市")
    private String city;

    @ExcelImport("头像")
    private String avatar;

    private String rowTips;

    private String rowData;
}
