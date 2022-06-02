package com.cc;

import lombok.extern.java.Log;
import org.junit.Test;

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
}
