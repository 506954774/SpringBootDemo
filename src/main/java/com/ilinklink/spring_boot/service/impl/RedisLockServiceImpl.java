package com.ilinklink.spring_boot.service.impl;

import com.ilinklink.spring_boot.model.SeckillParams;
import com.ilinklink.spring_boot.redis_distributed_lock.RedisDistributedLock;
import com.ilinklink.spring_boot.service.RedisLockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * RedisLockServiceImpl
 * 责任人:  ChenLei
 * 修改人： ChenLei
 * 创建/修改时间: 2021/3/26 16:34
 * Copyright :  版权所有
 **/
@Slf4j
@Service
public class RedisLockServiceImpl  implements RedisLockService {


    @Override
    @RedisDistributedLock(
            idIndex = 0, fieldName = "goodsSkuId",
            redisKeyPrefix = "GOODS_SECKILL_",redisKeySuffix = "",
            lockTime = 20000L,
            blockingHint = "当前访问人数较多,请稍后再试!"
    )
    public String seckill(SeckillParams params) throws RuntimeException {

        log.info("id为{}的商品,抢到了锁");
        try {
            Thread.sleep(8000L);
        } catch (InterruptedException e) {
        }
        return "抢购成功!";
    }
}
