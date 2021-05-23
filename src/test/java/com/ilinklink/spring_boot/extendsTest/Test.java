package com.ilinklink.spring_boot.extendsTest;

import java.util.Hashtable;

/**
 * Test
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2021/2/3  12:45
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
public class Test {

    public static void main(String[] args) {
        Hashtable<String,String> table=new Hashtable<>();
        table.put("a","b");
        System.out.println(table.get("a"));
        new B();
        new B();
    }
}
