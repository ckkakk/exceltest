package com.cc;

import cn.hutool.core.util.HexUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

public class t1 {

    @Test
    public void tMap(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("a","xswl");
        map.put("b","awsl");
        map.put("c","yyds");

        Map<String, String> newmap = new HashMap<String, String>();
        newmap.putAll(map);
        System.out.println(map);
        System.out.println(newmap);

        String str = new String();
        str = map.toString();
        System.out.println("A"+str+"a1\r\n"+str+"a2\n"+str+"a3");
    }

    @Test
    public void tstr(){
        String result = "{\"success\":true,\"code\":\"200\",\"message\":\"操作成功\"," +
                "\"data\":{\"hospitalInfo\":\"cCasPii07gV7\",\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
                "eyJjb21wYW55SWQiOiJjQ2FzUGlpMDdnVjciLCJleHAiOjE2NTM2Mjg0OTgsInVzZXJuYW1lIjoiMTgwODk2MjY2ODIifQ.Scx9SkdXi6ZD" +
                "6D3mbvT6QZCXMeYDYNQtSVws-KlzfD0\"}}";
        int hosInfo = result.indexOf("hospitalInfo");
        int tok = result.indexOf("token");
        String hos = result.substring(hosInfo+15,hosInfo+27);
        String Token = result.substring(tok+8, result.length()-3);
        System.out.println(tok  + " " + hosInfo);
        System.out.println(hos);
        System.out.println(Token);

        String gg = new String("dddd ffff jjjj data: tuggg\"\"");
        String a = gg.substring(gg.indexOf("data")+6,gg.length()-1);
        System.out.println(a);
    }

    @Test
    public void ttjiema(){
        EncrypAES encrypAES = null;
        String s = new String();
        try {
            encrypAES = new EncrypAES("cCasPii07gV7");
            String info = HexUtil.encodeHexStr("{wasteName:感染性废物,departmentTypeId:KoCjj4eSyQ5ZPPsEVGLD1c,hospitalId:cCasPii07gV7,wasteWeight:12.5,departmentId:2mw2wxLSAF4pkaYMhBFoi9,printDate:2022-05-23 10:04:42,wasteNumber:6666666666666666667},{wasteName:感染性废物,departmentTypeId:KoCjj4eSyQ5ZPPsEVGLD1c,hospitalId:cCasPii07gV7,wasteWeight:12.6,departmentId:2mw2wxLSAF4pkaYMhBFoi9,printDate:2022-05-23 11:04:42,wasteNumber:6666666666666666668}");
            s = encrypAES.decryptor("073DED39FF735A4F2A831A95FFB3AB728E7B46BCE34562B1BFBECB0D4EBC8229");
//                constructId = new String(bytes, "UTF-8");

            System.out.println("info:  " + info);
            System.out.println("constructId:  " + s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void toei() throws ParseException {

        Double d1 = new Double("1.2");
        Double d2 = new Double("3.6");

        double d3 = 5;
        double d4 = 5;

        String regex = "(19[0-9]{2}|20([0-9]{2}))-" +
                "((0[469]|11)-([012][0-9]|30)|(0[13578]|1[02])-([012][0-9]|3[01])|(02-([01][0-9]|2[0-8])))";

        String date = "0022-21-11-99";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        Date parse = simpleDateFormat.parse(date);

        System.out.println(parse);

        System.out.println(Pattern.matches(regex, date));

        System.out.println(d3 + ":" + d4 +(d4 == d3));

    }

    @Test
    public void dfa() throws UnsupportedEncodingException {
        String stringSignTemp="bar=2&foo=1&foo_bar=3&foobar=41e8k51b7c8acb5d5a0a30e72s3c23de6";
        String sign= DigestUtils.md5Hex(stringSignTemp.getBytes()).toUpperCase();

        System.out.println(sign);


        String stringSignTemp1="bar=2&foo=1&foo_bar=3&foobar=4&time=202110201335&salt=333799664838888888";
        String sign1= DigestUtils.md5Hex(stringSignTemp1.getBytes()).toUpperCase();

        System.out.println(sign1);

        String string = new String("zmcdsb!rietutioruet");

        try {
            String newString = new String(string.getBytes("UTF-8"), "UTF-8");
            if (newString.equals(new String(newString.getBytes(), "GBK"))) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Properties initProp = new Properties(System.getProperties());
        System.out.println("当前系统编码:" + initProp.getProperty("file.encoding"));
        System.out.println("当前系统语言:" + initProp.getProperty("user.language"));

        byte[] bytes = string.getBytes("UTF-8");
        for (byte b : bytes){
            System.out.print(b);
        }

        System.out.println();

        String ss = new String("擦拭".getBytes(),"gbk");
        String ss1 = new String("擦拭".getBytes(),"utf-8");
        System.out.println(DigestUtils.md5Hex(ss));
        System.out.println(DigestUtils.md5Hex(ss1));

        System.out.println("ss:"+ ss + "   ss1:"+ ss1);

        byte[] a = "官方的".getBytes("utf-8");
        for (byte b : a){
            System.out.print(b);
        }

        System.out.println();

        String s = new String(bytes, "utf-8");
        System.out.println(s);

        String sss = new String(("delete_flag=0&nurse_code=0028010001A2049&nurse_name=LISILLLLLLLLL&org_id=0514010001" +
                "&time=1652956399981&salt=6D9C5F55320F07AAEEF4DD9BF6ED6D49").getBytes(),"UTF-8");
        System.out.println(DigestUtils.md5Hex(sss).toUpperCase());
        System.out.println(DigestUtils.md5Hex(DigestUtils.md5Hex(sss).toUpperCase()));
        String sss1 = new String(("delete_flag=0&org_id=0514010001&recycle_user_id=0000010001A1001&" +
                "recycle_user_name=赵清理&time=1652944480258&salt=6D9C5F55320F07AAEEF4DD9BF6ED6D49").getBytes(), "ISO-8859-1");
        System.out.println(sss1);
        System.out.println(DigestUtils.md5Hex(sss1).toUpperCase());
    }

    private void threadTest() throws InterruptedException {
        Date date = new Date();

        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName() + " == nn");

    }

}
