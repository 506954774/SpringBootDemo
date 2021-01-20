package com.ilinklink.spring_boot.ioc;

import org.apache.ibatis.binding.MapperProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * IocTestServiceBeanFactory
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2021/1/20  18:00
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
public class IocTestServiceBeanFactory<T>  {

    private final Class<T> mapperInterface;

    public IocTestServiceBeanFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public Class<T> getMapperInterface() {
        return mapperInterface;
    }


    @SuppressWarnings("unchecked")
    public T newInstance() {
        //用JDK自带的动态代理生成映射器
        return (T) Proxy.newProxyInstance(IocTestServiceBeanFactory.class.getClassLoader(), new Class[] { mapperInterface }, new CustomProxy());
    }



}
