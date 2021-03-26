package com.ilinklink.spring_boot.redis_distributed_lock;


import com.ilinklink.spring_boot.ServerConstants;
import com.ilinklink.spring_boot.aop.checkParams.CheckParams;
import com.ilinklink.spring_boot.aop.checkParams.NeedCheckParams;
import com.ilinklink.spring_boot.exception.AdminErrorCode;
import com.ilinklink.spring_boot.exception.AdminException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * RedisDistributedLockHandler
 *  * 责任人:  chenlei
 *  * 修改人： chenlei
 *
 * 核心功能：AOP实现redis分布式锁,从参数中取出id,针对此id使用redis的setNx命令加一个带有时效性的锁.
 * 使用场景:抢购秒杀
 **/
@Component
@Aspect
@Slf4j
public class RedisDistributedLockHandler implements ApplicationContextAware {

    public static final String TAG="分布式锁-";

    private ApplicationContext applicationContext;

    @Autowired
    private RedisTemplate redisTemplate;

    @Around(value = "@annotation(redisDistributedLock)")
    public Object redisLock(ProceedingJoinPoint jp, RedisDistributedLock redisDistributedLock) throws Exception{

        try {
             Object result=null;

             String blockingHint = redisDistributedLock.blockingHint();//阻塞时报错

                //框架拿到的入参集合
                Object[] args = jp.getArgs();

                //如果方法是无参的，则直接抛出异常
                if(args==null||args.length==0){
                    throw new RuntimeException(blockingHint);
                }
                else{
                    //从IOC容器中获取redisTemplateBean
                    //RedisTemplate redisTemplate = (RedisTemplate) this.applicationContext.getBean(redisDistributedLock.redisTemplateBean());

                    //入参中,哪个参数里包含了id
                    int idIndex = redisDistributedLock.idIndex();
                    //id的字段名,为空则是参数本身toString()
                    String fieldName = redisDistributedLock.fieldName();
                    //前缀
                    String redisKeyPrefix = redisDistributedLock.redisKeyPrefix();
                    //后缀
                    String redisKeySuffix = redisDistributedLock.redisKeySuffix();
                    //redis锁的过期时间
                    long lockTime = redisDistributedLock.lockTime();

                    //储存id,格式 {前缀}id{后缀}
                    StringBuilder stringBuilder=new StringBuilder();

                    if(idIndex>=0&&idIndex<args.length){
                        Object param=args[idIndex];
                        if(param!=null){
                            if(fieldName==null||"".equals(fieldName.trim())){
                                stringBuilder.append(redisKeyPrefix);
                                stringBuilder.append(param);
                                stringBuilder.append(redisKeySuffix);
                            }
                            else {
                                //反射拿到id
                                Field idField = param.getClass().getDeclaredField(fieldName);
                                idField.setAccessible(true);
                                stringBuilder.append(redisKeyPrefix);
                                stringBuilder.append(idField.get(param));
                                stringBuilder.append(redisKeySuffix);
                            }

                            //锁key,不管多少个应用服务,某一时刻只能处理一个
                            String redisKey=stringBuilder.toString();
                            //加上UUID,自己上的锁只能自己解开
                            String value= UUID.randomUUID().toString();

                            //设置分布式锁,key是锁id,value是当前应用进程标识,lockTime后自动删除.lockTime内能把业务做完,则不会有任何问题
                            boolean notBlocking=redisTemplate.opsForValue().setIfAbsent(redisKey,value,lockTime, TimeUnit.MILLISECONDS);

                            if(!notBlocking){
                                log.warn( TAG+blockingHint);
                                throw new RuntimeException(blockingHint);
                            }

                            //拿到锁了,继续做业务
                            try {
                                result=doExec(jp, args);
                                return result;
                            } catch (Exception e) {
                                log.error( TAG+"异常:"+ Arrays.toString(e.getStackTrace()));
                                throw new RuntimeException(blockingHint);
                            }

                            //如果实际业务里抛异常了,最终释放当前锁
                            finally {
                                ValueOperations<String, String> vopt = redisTemplate.opsForValue();
                                //自己才能删除自己的锁
                                if(value.equals(vopt.get(redisKey))){
                                    //不论过程如何,最后把锁删掉
                                    redisTemplate.delete(redisKey);
                                    log.warn(TAG + "释放分布式锁,value:" + value);
                                }
                            }

                        }
                        else {
                            throw new RuntimeException(blockingHint);
                        }

                    }
                    else {
                        throw new RuntimeException(blockingHint);
                    }

                }

        } catch (Exception e) {
            throw new RuntimeException(e);
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
            log.error(ServerConstants.LOGGER_PREFIX + "[redis分布式锁,AOP操作异常]，方法签名："+jp.toString()+",参数:"+Arrays.toString(args), throwable);
            throw new Exception(throwable);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
         this.applicationContext=applicationContext;
    }
}























