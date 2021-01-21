package com.ilinklink.spring_boot.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

/**
 * MyInstantionAwearPost
 * 后置处理器，ioc bean实例化的第一个后置处理器
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2021/1/21  10:46
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@Configuration
public class MyInstantionAwearPost implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {

       /* if(beanClass==IocTestService.class){
            return  Proxy.newProxyInstance(MyInstantionAwearPost.class.getClassLoader(), new Class[] { IocTestService.class }, new CustomProxy());
        }*/
        if(beanName.equals("iocTestService")){
            return  (IocTestService)Proxy.newProxyInstance(MyInstantionAwearPost.class.getClassLoader(), new Class[] { IocTestService.class }, new CustomProxy());
        }
        return null;
    }
}
