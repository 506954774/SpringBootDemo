package com.ilinklink.spring_boot.action;

import com.chuck.framework.redis_distributed_lock.RedisDistributedLock;
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

  /*  @ApiOperation(value = "测试分布式锁(商品秒杀抢购场景)", notes = "测试分布式锁(商品秒杀抢购场景)")
    @PostMapping("/secskill/local")
    public ResponseEntity reduceInventory(@RequestBody SeckillParams params) {
        try {
            String seckill = redisLockService.seckill(params);
            return new ResponseEntity<String>(seckill, true, null);
        } catch (AdminException e) {
            return new ResponseEntity<String>(" ", false, e.getMessage());
        }
    }*/


    @RedisDistributedLock(
            //GOODS_SECKILL_是redis存数据时,key的前缀,#params.goodsSkuId类似mybatis里,获取实际值,例如商品id
            keys = {"'GOODS_SECKILL_'+#params.goodsSkuId"},
            //锁最迟多久释放,毫秒
            lockTime = 20000L,
            //并发时,等待获取锁的时间,毫秒
            waitTime= 3000L,
            //报错提示,将以RuntimeException的形式抛出
            blockingHint = "当前抢购人数太多,请稍后再试!"
    )
    @ApiOperation(value = "测试分布式锁(商品秒杀抢购场景)", notes = "测试分布式锁(商品秒杀抢购场景)")
    @PostMapping("/secskill")
    public ResponseEntity reduceInventory2(@RequestBody SeckillParams params) {
        log.info("抢到了id为{}的商品",params.getGoodsSkuId());
        try {
            Thread.sleep(1500L);//模拟耗时操作
        } catch (InterruptedException e) {
        }
        return new ResponseEntity<String>("000000", true, "抢购成功!");
    }




}
