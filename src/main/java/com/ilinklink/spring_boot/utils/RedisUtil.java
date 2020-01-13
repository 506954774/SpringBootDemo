package com.ilinklink.spring_boot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Jason on 2019/10/23.
 */
@Slf4j
@Service
public class RedisUtil {

    private static final Long RELEASE_SUCCESS = 1L;
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "EX";
    private static final String RELEASE_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    /**
     * 祖先-子孙关系表
     */
    public static final String ANCESTOR_DESCENDANT_RELATION_TABLE = "ANCESTOR_DESCENDANT_RELATION";
    /**
     * 子孙-祖先关系表
     */
    public static final String DESCENDANT_ANCESTOR_RELATION_TABLE = "DESCENDANT_ANCESTOR_RELATION";

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public void set(String key, String value, long time) {
        ValueOperations<String, String> vopt = redisTemplate.opsForValue();
        vopt.set(key, value, time, TimeUnit.SECONDS);
    }

    public void set(String key, String value) {
        ValueOperations<String, String> vopt = redisTemplate.opsForValue();
        vopt.set(key, value);
    }

    public String get(String key) {
        ValueOperations<String, String> vopt = redisTemplate.opsForValue();
        return vopt.get(key);
    }


    public void hdel(String field) {
        redisTemplate.delete(field);
    }


    public static void main(String[] args) {

    }
}
