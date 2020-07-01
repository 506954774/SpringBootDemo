package com.ilinklink.spring_boot.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

/**
 * ChuckAopSetField
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/6/28  18:24
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@Component
@Aspect
@Slf4j
public class ChuckAopSetField implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @AfterReturning(value = "@annotation(com.ilinklink.spring_boot.aop.NeedSetVaule)",returning = "ret")
    public Object doSetField(Object ret) throws Exception{
        if(ret==null){
            return null;
        }
        else {

            Class <?> clazz=ret.getClass();
            Field[] fields = clazz.getDeclaredFields();
            if(fields!=null ){
                for (Field needField : fields) {

                    needField.setAccessible(true);

                    SetVaule sv=needField.getAnnotation(SetVaule.class);//看某个字段是否有加注释

                    if(sv==null){
                        continue;
                    }


                //获取dao层bean
                Object bean = this.applicationContext.getBean(sv.beanClass());

                //获取入参字段
                Field paramField=clazz.getDeclaredField(sv.paramField());
                paramField.setAccessible(true);

                //获取入参，从对象里取出入参值
                Object paramValue=paramField.get(ret);

                //获取调用
                    // 哪个方法
                Method method=sv.beanClass().getMethod(sv.method(),paramField.getType());

                if(paramValue!=null){

                    Object value=method.invoke(bean,paramValue);//反射调用dao层的方法:调用MemberService的queryUser（id）方法

                    if(value!=null){//取出user的userName赋值给order的customerName
                        Field targetField=value.getClass().getDeclaredField(sv.targetField());
                        targetField.setAccessible(true);
                        value = targetField.get(value);//把user里的userName取出

                        //field直接设值：给对象
                        needField.set(ret,value);
                    }
                }

                }

                log.error("aop之后，结果：{}",ret);
                return  ret;
            }

        }
        return  null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
         this.applicationContext=applicationContext;
    }
}























