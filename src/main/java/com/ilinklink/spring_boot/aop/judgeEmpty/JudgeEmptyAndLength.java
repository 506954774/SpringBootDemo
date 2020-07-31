package com.ilinklink.spring_boot.aop.judgeEmpty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * JudgeEmptyAndLength
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/6/28  17:32
 * Copyright : 2014-2015 深圳掌通宝科技有限公司-版权所有
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JudgeEmptyAndLength {
    int lengthMax();//字符串长度最大值，如果超过这个值，就报错
    String errorCodeForEmpty();//空时候的错误码
    String errorCodeForLength();//长度不合法时候的错误码
}
