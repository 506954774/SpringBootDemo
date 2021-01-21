package com.ilinklink.spring_boot.ioc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * CustomProxy
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2021/1/20  16:01
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
public class CustomProxy implements InvocationHandler {

    //如果需要什么参数，客户端传过来

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String param=args[0].toString();
        return "来自动态代理的bean，拼接，返回一个字符串["+param+"]";
    }
}
