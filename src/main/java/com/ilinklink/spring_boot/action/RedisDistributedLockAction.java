package com.ilinklink.spring_boot.action;

import com.ilinklink.spring_boot.exception.AdminException;
import com.ilinklink.spring_boot.model.SeckillParams;
import com.ilinklink.spring_boot.service.RedisLockService;
import com.ilinklink.spring_boot.web.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 *
 */
@Slf4j
@RestController
@RequestMapping("/redis_distributed_lock")
public class RedisDistributedLockAction extends ServerExceptionHandler{

    public static final String TAG="秒杀-";

    @Autowired
    private RedisLockService redisLockService;

    @ApiOperation(value = "测试分布式锁(商品秒杀抢购场景)", notes = "测试分布式锁(商品秒杀抢购场景)")
    @PostMapping("/secskill")
    public ResponseEntity reduceInventory(@RequestBody SeckillParams params) {
        try {
            String seckill = redisLockService.seckill(params);
            return new ResponseEntity<String>(seckill, true, null);
        } catch (AdminException e) {
            return new ResponseEntity<String>(" ", false, e.getMessage());
        }
    }




}
