package com.ilinklink.spring_boot.service.impl;

import com.ilinklink.spring_boot.model.SeckillParams;
import com.ilinklink.spring_boot.redis_distributed_lock.RedisLock;
import com.ilinklink.spring_boot.service.RedisLockService;
import lombok.extern.slf4j.Slf4j;
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
    @RedisLock(
            keys = {"#params.goodsSkuId"},
            redisKeyPrefix = "GOODS_SECKILL_",
            lockTime = 20000L,
            waitTime= 3000L,
            blockingHint = "当前抢购人数太多,请稍后再试!"
    )
    public String seckill(SeckillParams params) throws RuntimeException {

        log.info("抢到了id为{}的商品",params.getGoodsSkuId());
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
        }
        return "抢购成功!";
    }
}
