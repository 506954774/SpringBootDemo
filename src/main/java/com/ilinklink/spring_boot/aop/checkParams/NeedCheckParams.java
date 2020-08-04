package com.ilinklink.spring_boot.aop.checkParams;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * NeedCheckParams
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/6/28  17:32
 * Copyright : 2014-2020 深圳令令科技有限公司-版权所有
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedCheckParams {
    Class<?> serviceBeanClass();//service层class，因为错误码的获取是封装在baseService里的，所以必须提供一个class
    String method();//方法名:错误码由哪个方法提供,例如'getMessge'
    Class<?>[] paramsType();//参数类型
    String logPrefix();//日志的前缀
}
