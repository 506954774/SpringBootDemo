package com.ilinklink.spring_boot.redis_distributed_lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * RedisDistributedLock
 * 责任人:  chenlei
 * 修改人： chenlei
 * 创建/修改时间: 2020/6/28  17:32
 * Copyright : 2014-2020 深圳令令科技有限公司-版权所有
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisDistributedLock {
    Class<?> redisTemplateBean();//redisTemplate实例,最好自己通过@Configration和@Bean提供bean,如果直接用容器的,可能导致报错
    String redisKeyPrefix();//key的前缀
    int idIndex();//入参中携带id的参数的位置,实际项目里,多参数的方法里,此值表示哪个参数里可以获取id
    String fieldName();//参数中表示id的成员变量的名字,为空则表示参数本身,例如String,Long,int类型的参数,本身就可以是id
    String redisKeySuffix();//key的后缀
    long lockTime() default 10000L;//锁的时间,到期自动释放,单位毫秒
    String blockingHint() default "当前访问人数较多,请稍后再试!";//阻塞时的提示信息
}
