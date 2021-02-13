package com.ilinklink.spring_boot.extendsTest;

/**
 * A
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2021/2/3  12:42
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
public class B extends A{

    {
        System.out.println("子类代码块");
    }

    static {
        System.out.println("子类静态代码块");
    }

    public B(){
        System.out.println("子类无参构造");
    }

}
