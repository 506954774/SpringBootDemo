package com.ilinklink.spring_boot.ioc;

/**
 * IocTestService
 * 控制反转，容器测试，这个service没有实现类，通过类定义注册的后置处理器，给ioc容器强制加一个，以动态代理的方式，以此保证拿到此service的bean
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2021/1/20  14:20
 * @version 1.0.0
 * Copyright : 2014-2017 深圳令令科技有限公司-版权所有
 **/
public interface IocTestService {
    String test(String msg);
}
