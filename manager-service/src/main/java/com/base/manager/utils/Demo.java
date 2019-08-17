package com.base.manager.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 测试
 * @author: dengqx
 * @createTime: 2019-08-11 12:14
 */
public class Demo {
    public static void main(String[] args) {
        /*List<Integer> list = Arrays.asList(new Integer[]{1,2,null,3,null,4,5,6});
        List<Integer> lowercaseNames1 = list.stream().filter(item -> item != null).map(num -> num * 2).collect(Collectors.toList());
        lowercaseNames1.forEach(System.out::println);*/
        /*List<String> proNames = Arrays.asList(new String[]{"Ni","Hao","Lambda"});
        List<String> lowercaseNames1 = proNames.stream().map(name -> {return name.toLowerCase();}).collect(Collectors.toList());
        lowercaseNames1.forEach(System.out::println);*/
        /*List<String> list = new ArrayList<>();
        list.add("HELLO");
        list.add("WORD");
        list.forEach(String::toLowerCase);
        System.out.println(list);*/
        Thread td = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1111);
            }
        });
        td.start();



    }
}