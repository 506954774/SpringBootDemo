package com.ilinklink.spring_boot.service.impl;

import com.ilinklink.spring_boot.service.RedisClusterService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * RedisClusterServiceImpl
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2019/11/28  19:43
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@Slf4j
@Service
public class RedisClusterServiceImpl extends BaseService implements RedisClusterService {

 
    @Value(value = "${redisCluster.host}")
    private String redisClusterHost="";



    @Override
    public void set(String key, String vaule) {

        log.info("redisClusterHost:"+redisClusterHost);

        Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
        jedisClusterNode.add(new HostAndPort(redisClusterHost,8001));
        jedisClusterNode.add(new HostAndPort(redisClusterHost,8002));
        jedisClusterNode.add(new HostAndPort(redisClusterHost,8003));
        jedisClusterNode.add(new HostAndPort(redisClusterHost,8004));
        jedisClusterNode.add(new HostAndPort(redisClusterHost,8005));
        jedisClusterNode.add(new HostAndPort(redisClusterHost,8006));

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(10);
        config.setTestOnBorrow(true);
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNode, 6000, 10, config);
        jedisCluster.set(key,vaule);


        try {
            jedisCluster.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String get(String key) {
        String result=null;
        Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
        jedisClusterNode.add(new HostAndPort(redisClusterHost,8001));
        jedisClusterNode.add(new HostAndPort(redisClusterHost,8002));
        jedisClusterNode.add(new HostAndPort(redisClusterHost,8003));
        jedisClusterNode.add(new HostAndPort(redisClusterHost,8004));
        jedisClusterNode.add(new HostAndPort(redisClusterHost,8005));
        jedisClusterNode.add(new HostAndPort(redisClusterHost,8006));

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(10);
        config.setTestOnBorrow(true);
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNode, 6000, 10, config);
        result=jedisCluster.get(key);

        try {
            jedisCluster.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return result;
        }
    }
}
