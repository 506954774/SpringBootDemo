package com.ilinklink.spring_boot.aop.checkParams;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * CheckParams
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/6/28  17:32
 * Copyright : 2014-2020 深圳令令科技有限公司-版权所有
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckParams {
    String regularExpression();//正则表达式
    String errorCodeForEmpty();//字段为空时候的错误码
    String errorCodeForIllegal();//字符串不合法(正则匹配)时候的错误码
}
