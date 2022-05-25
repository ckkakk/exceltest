package com.cc.cv;


import com.alibaba.fastjson.JSONArray;
import com.zyq.utils.excel.ExcelUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


@RestController
public class ette {

    @RequestMapping("/exceyltt")
    public String wrer(@RequestPart("file") MultipartFile file) {
        List<User> users = null;
        try {
            users = ExcelUtils.readMultipartFile(file, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (User user : users) {
            System.out.println(user.toString());
        }
        return "xsnmyzn";
    }

    @PostMapping("/import")
    public void upload(@RequestPart("file") MultipartFile file) throws Exception {
        Map<String, JSONArray> map = ExcelUtils.readFileManySheet(file);
        map.forEach((key, value) -> {
            System.out.println("Sheet名称：" + key);
            System.out.println("Sheet数据：" + value);
            System.out.println("----------------------");
        });
    }
}
