package com.cc.cv;


import com.alibaba.fastjson.JSONArray;
import com.cc.annotation.Log;
import com.zyq.utils.excel.ExcelUtils;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@RestController
public class ette {

    @Log("gdfgfgaf")
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
        String s = "dfgdsg";
        return s;
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

    @Log("不可能")
    @GetMapping("/yyds")
    public String yyds(){
        String ss = "乐死你了";
        System.out.println(ss);
        return ss;
    }

    @Log("dao出")
    @GetMapping("/export")
    public void exportexcel(HttpServletResponse response) throws MalformedURLException {
        List<Object> head = Arrays.asList("姓名","年龄","性别","二维码");
        File file = new File("E:\\gitdownProgram\\66.jpg");
        URL url = new URL("file:///E:\\gitdownProgram\\66.jpg");

        List<Object> user1 = new ArrayList<>();
        user1.add("张敏聪");
        user1.add(60);
        user1.add("男");
        user1.add(url);

        List<List<Object>> datasheet = new ArrayList<>();
        datasheet.add(head);
        datasheet.add(user1);
        ExcelUtils.export(response,"aa",datasheet);
    }
}
