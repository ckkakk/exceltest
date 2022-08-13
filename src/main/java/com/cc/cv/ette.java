package com.cc.cv;


import com.alibaba.fastjson.JSONArray;
import com.cc.annotation.Log;
import com.zyq.utils.excel.ExcelUtils;


import lombok.Data;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;


@RestController
public class ette {
    private LinkedBlockingQueue<Request> queue = new LinkedBlockingQueue<>();

    @Log("cookieTest")
    @RequestMapping("coosess")
    public String coosess(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession();

        session.setAttribute("user", "用户信息");
        Cookie cookie = new Cookie("usr",session.getId());

        response.addCookie(cookie);
//        System.out.println(cookies.length);
        return "ok";
    }

    @Log("cookieTest")
    @RequestMapping("getcoosess")
    public String getcoosess(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies.length);
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        System.out.println(user);
        return "ok";
    }

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

//        Tusr tusr = new Tusr();
//        tusr.setName("张敏聪");
//        tusr.setAge(60);
//        tusr.setSex("男");
//        tusr.setUrl(url);

        List<List<Object>> datasheet = new ArrayList<>();
        datasheet.add(head);
        datasheet.add(user1);
//        datasheet.add(tusr);
        ExcelUtils.export(response,"aa",datasheet);
    }

    @GetMapping("/66")
    public void batchQuery(){
        Thread thread[]=new Thread[1000];
        for (int i = 0; i <1000 ; i++) {
            int j=i;
            thread[i]=new Thread(new Runnable() {
                @Override
                public void run() {
                    queryItem(j + "");
                }
            });
            thread[i].start();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Data
    class Tusr{
        private String name;

        private Integer age;

        private String sex;

        private URL url;
    }


    class Request {
        String code;
        CompletableFuture<Map<String, Object>> future;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public CompletableFuture<Map<String, Object>> getFuture() {
            return future;
        }

        public void setFuture(CompletableFuture<Map<String, Object>> future) {
            this.future = future;
        }
    }

    @PostConstruct
    public void init() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {

            int size = queue.size();
            if (size == 0) {
                return;
            }

            List<Request> requests = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                Request request = queue.poll();
                requests.add(request);
            }
            System.out.println("批量处理了" + size + "条请求");

            List<String> codes = new ArrayList<>();
            for (Request request : requests) {
                codes.add(request.getCode());
            }

//            List<Map<String, Object>> responses = queryItemService.queryByCodes(codes);
            List<Map<String, Object>> responses = new ArrayList<>();

            //结果集完成--> 把请求分发给每一个具体的Request
            Map<String, Map<String, Object>> responseMap = new HashMap<>();
            for (Map<String, Object> response : responses) {
                String code = response.get("code").toString();
                responseMap.put(code, response);
            }

            //返回请求
            for (Request request : requests) {
                Map<String, Object> result = responseMap.get(request.getCode());
                request.future.complete(result);
            }

        }, 0, 200, TimeUnit.MILLISECONDS);
    }

    public Map<String, Object> queryItem(String code) {
        Request request = new Request();
        request.setCode(code);

        CompletableFuture<Map<String, Object>> future = new CompletableFuture<>();
        request.setFuture(future);

        queue.add(request);

        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
