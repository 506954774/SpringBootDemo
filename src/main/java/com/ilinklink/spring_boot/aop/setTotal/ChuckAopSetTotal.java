package com.ilinklink.spring_boot.aop.setTotal;



import com.ilinklink.spring_boot.ServerConstants;

import org.apache.http.util.TextUtils;
import org.aspectj.lang.JoinPoint;
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
 * ChuckAopSetTotal
 * 核心功能：切面操作：在框架返回了某个对象后，拿到对象，继续操作，给对象添加附加属性
    场景：
    一个service层查出了一个订单列表，但是没有总数（分页的业务）,传统的做法是在service层再调用一次mapper的获取总数的方法，并赋值。对于这类相似的操作，本组件将由框架来代替它
    具体实现：通过容器拿到一个mapper对象，通过JoinPoint拿到参数，反射调用mapper，取到总数，赋值给结果。
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/6/28  18:24
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@Component
@Aspect
@Slf4j
public class ChuckAopSetTotal implements ApplicationContextAware {

    private ApplicationContext applicationContext;


    /**
     *
     *      凡是加了NeedSetTotal注解的方法，
     *     在返回值之后，会走这个方法。
     *     这个方法通过反射，拿到dao层的bean，反射调用dao的方法，把列表总数查出来，赋值给原来对象，实现分页
     * @param jp
     * @param ret
     * @param needSetTotal
     * @return
     * @throws Exception
     */
    @AfterReturning(value = "@annotation(needSetTotal)",returning = "ret")
    public Object doSetField(JoinPoint jp, Object ret, NeedSetTotal needSetTotal) throws Exception{
        try {
            if(ret==null){
                return ret;
            }
            else {
                //注解里设置的，参数的位置值。例如“0,1,2”表示我们需要前三个参数，作为调用mapper方法的参数
                String paramIndex=needSetTotal.paramIndexs();
                Class  <?> []  paramClazzs=null;
                Object[] args = jp.getArgs();//框架拿到的入参集合，它可能除了我们需要的参数外，还包含其他的参数，例如session里获取到的userId
                Object[] targetArgs = null;

                if(TextUtils.isEmpty(paramIndex)){//空串表示0，也就是将会取第一个参数作为最终的入参
                    paramIndex="0";
                }
                if("-1".equals(paramIndex)){//-1表示将会调用mapper无参的方法

                }
                else {
                    if(!TextUtils.isEmpty(paramIndex)){
                        int count= paramIndex.split(",").length;
                        paramClazzs=new Class  <?> [count];
                        targetArgs=new Object[count];
                    }
                    for (int i = 0; i < targetArgs.length; i++) {
                        paramClazzs[i]=args[i].getClass();
                        targetArgs[i]=args[i];
                    }
                }

                Class <?> clazz=ret.getClass();
                Field[] fields = clazz.getDeclaredFields();
                if(fields!=null ){
                    for (Field needField : fields) {
                        needField.setAccessible(true);
                        SetTotal sv=needField.getAnnotation(SetTotal.class);//看某个字段是否有加注释
                        if(sv==null){
                            continue;
                        }
                        //获取dao层bean
                        Object bean = this.applicationContext.getBean(sv.beanClass());

                        //获取将调用的方法
                        Method method=sv.beanClass().getMethod(sv.method(),paramClazzs);

                        Object value=method.invoke(bean,targetArgs);//反射调用dao层的方法:调用orderMapper的countOrders（BaseParams params）方法

                        if(value!=null){//查出总数，默认是int
                            int count= (int) value;
                            //field直接设值：给对象
                            needField.set(ret,count);
                        }
                    }
                    return  ret;
                }
            }
            return ret;
        } catch (Exception e) {
            log.error(ServerConstants.LOGGER_PREFIX + "[AOP操作异常]，方法签名："+jp.toString(), e);
            throw e;
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
         this.applicationContext=applicationContext;
    }
}























