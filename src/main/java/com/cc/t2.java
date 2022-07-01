package com.cc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.java.Log;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.junit.Test;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;

public class t2 {
    @Test
    public void tfor(){
        int[] s = new int[]{4, 2, 9, 9, 4, 5, 8, 7, 5, 2, 1};
        for (int i : s) {
            System.out.print(i);
            System.out.println(UUID.randomUUID().toString().toUpperCase());
        }

        System.out.println();

        Map<String, String> map = new HashMap<>();
        map.put("1", "sd");
        map.put("2", "sb");
        map.put("3", "lyb");
        map.put("4", "zmc");

        Entry<String, String> entry;

        Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            entry = iterator.next();
            System.out.println(entry.getKey() + " 对 " + entry.getValue());
        }
    }


    /**
     *  日历测试
     */
    @Test
    public void ttime(){
        System.out.println(LocalDate.now());
        Date date = new Date();
        System.out.println(date);
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.MONTH));
        System.out.println(calendar.get(Calendar.AM_PM));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println(calendar);
        Float f = new Float(2.36);
        System.out.println(f.toString());
        List<String> list = new ArrayList<>();
        for (String s : list) {
            System.out.println(s);
        }

        System.currentTimeMillis();
    }

    private static final int BYTES_DEFAULT_LENGTH = 10240;

    /**
     *  图片流
     */
    @Test
    public void tPicture() throws MalformedURLException {

        URL url = new URL("");

        try (InputStream is = url.openStream(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buff = new byte[BYTES_DEFAULT_LENGTH];
            int rc;
            while ((rc = is.read(buff, 0, BYTES_DEFAULT_LENGTH)) > 0) {
                outputStream.write(buff, 0, rc);
            }


            // 设置图片位置
//            XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, y, x, y + 1, x + 1);
            // 设置这个，图片会自动填满单元格的长宽
//            anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);
//            patriarch.createPicture(anchor, wb.addPicture(outputStream.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void tjson(){

        System.out.println(new BigDecimal("1.132123123123123"));
        Preson p = new Preson();
        Preson p2 = new Preson();
        p.name = "aa";
        p.age = 15;
        p.sex = 1;
        p2.name = "bb";
        p2.age = 20;
        p2.sex = 2;
        List<Object> list = new ArrayList();
        list.add(p);
        list.add(p2);
        JSONArray jsonArray = new JSONArray(list);

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            Preson parse = JSONArray.parseObject(String.valueOf(object), Preson.class);
            System.out.println(parse);
        }
    }

    @Data
    static class Preson{
        private String name;
        private Integer age;
        private Integer sex;
    }
}
