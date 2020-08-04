package com.ilinklink.spring_boot.aop.checkParams;


 

import com.ilinklink.spring_boot.ServerConstants;
import com.ilinklink.spring_boot.exception.AdminErrorCode;
import com.ilinklink.spring_boot.exception.AdminException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

/**
 * ChuckCheckParams
 * 核心功能：在目标方法执行之前，校验参数的合法性（目前只针对String字段和Date字段），String字段包括非空验证和正则表达式验证
 **/
@Component
@Aspect
@Slf4j
public class ChuckCheckParams implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Around(value = "@annotation(needCheckParams)")
    public Object doJudgeEmptyAndLength(ProceedingJoinPoint jp, NeedCheckParams needCheckParams) throws Exception{
        try {
                //获取service的bean,这个bean提供方法,把errorCode变为errorMessage
                Object serviceBean = this.applicationContext.getBean(needCheckParams.serviceBeanClass());
                //获取方法,将会调用此方法,获取配置文件里的错误message
                String method = needCheckParams.method();
                //获取错误码的参数类型,本项目里是String.class
                Class<?>[] paramsClasses = needCheckParams.paramsType();
                //获取注解里设置的日志前缀，以便出现问题时排查异常
                String logPrefix=needCheckParams.logPrefix();

                Object[] args = jp.getArgs();//框架拿到的入参集合，它可能除了我们需要的参数外，还包含其他的参数，例如session里获取到的userId

                //如果方法签名，就是无参的，则直接执行加了注解的那个方法
                if(args==null||args.length==0){
                    return doExec(jp, args);
                }
                else{

                    //直接反射拿到配置的日志service提供的getMessage方法，以便直接拿到中文或者英文的错误码对应的提示文本
                    Method serviceErrorCodemethod= needCheckParams.serviceBeanClass(). getMethod(method,paramsClasses);
                    serviceErrorCodemethod.setAccessible(true);

                    //遍历入参的每一个字段：
                    for (int i = 0; i < args.length; i++) {
                        if(args[i]==null){
                            String error= (String) serviceErrorCodemethod.invoke(serviceBean,AdminErrorCode.PARAMS_EMPTY);//反射调用方法:从配置文件里拿到 "请求参数为空" 的错误码
                            log.warn(logPrefix+error);//日志输出
                            throw new AdminException(AdminErrorCode.PARAMS_EMPTY, error);
                        }
                        Class <?> clazz=args[i].getClass();
                        Field[] fields = clazz.getDeclaredFields();
                        if(fields!=null ){
                            for (Field needField : fields) {
                                needField.setAccessible(true);
                                CheckParams sv=needField.getAnnotation(CheckParams.class);//看某个字段是否有加注释
                                if(sv==null){
                                    continue;
                                }

                                //只处理字符串和date的字段
                                if(needField.getType() !=String.class && needField.getType() != Date.class){
                                    continue;
                                }

                                String regularExpression = sv.regularExpression();//正则表达式
                                String errorCodeEmpty=sv.errorCodeForEmpty();//获取注解里配置的为空时的错误码
                                String errorCodeForIllegal=sv.errorCodeForIllegal();//获取注解里配置的不满足正则表达式时候的错误码

                                if(needField.getType() == Date.class){//date类型,只判断是否为空
                                    Date field= (Date) needField.get(args[i]);
                                    if(field==null){
                                        String error= (String) serviceErrorCodemethod.invoke(serviceBean,errorCodeEmpty);//反射调用方法:从配置文件里拿到 xx字段为空的错误码
                                        log.warn(logPrefix+error);//日志输出
                                        throw new AdminException(errorCodeEmpty, error);
                                    }
                                }
                                else if(needField.getType() == String.class){
                                    String field= (String) needField.get(args[i]);
                                    if(field==null||"".equals(field.trim())){
                                        String error= (String) serviceErrorCodemethod.invoke(serviceBean,errorCodeEmpty);//反射调用方法:从配置文件里拿到 xx字段为空的错误码
                                        log.warn(logPrefix+error);//日志输出
                                        throw new AdminException(errorCodeEmpty, error);
                                    }

                                    field=field.trim();//去空格
                                    boolean flag = field.matches(regularExpression);//正则匹配
                                    if(!flag){
                                        String error= (String) serviceErrorCodemethod.invoke(serviceBean,errorCodeForIllegal);//反射调用方法:从配置文件里拿到 xx字段不合法 的错误码
                                        log.warn(logPrefix+error);//日志输出
                                        throw new AdminException(errorCodeEmpty, error);
                                    }
                                }
                                else {//其他类型字段暂时不判断
                                    continue;
                                }
                            }
                        }
                    }
                }

            return doExec(jp, args);//数据没问题了，则直接执行该方法

        } catch (Exception e) {
            if(e instanceof AdminException){//业务异常
            }
            else {
                log.error(ServerConstants.LOGGER_PREFIX + "[AOP操作异常]，方法签名："+jp.toString(), e);
                throw e;
            }
            throw e;
        }

    }

    /**
     * 继续执行方法
     * @param jp
     * @param args
     * @return
     * @throws Exception
     */
    private Object doExec(ProceedingJoinPoint jp, Object[] args) throws Exception {
        try {
            Object result = jp.proceed(args);
            //如果这里不返回result，则目标对象实际返回值会被置为null
            return result;
        } catch (Throwable throwable) {
            log.error(ServerConstants.LOGGER_PREFIX + "[AOP操作异常]，方法签名："+jp.toString(), throwable);
            if(throwable instanceof AdminException){//兜住业务异常
                AdminException e = (AdminException) throwable;
                throw  e;
            }
            else {
                throw new Exception();
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
         this.applicationContext=applicationContext;
    }
}























