package com.ilinklink.spring_boot.action;

import com.ilinklink.spring_boot.web.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin.util.UIUtil;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * Created by Jason on 2019/11/4.
 */
@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisAction extends ServerExceptionHandler{

    public static final String TAG="秒杀-";

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "测试分布式锁(商品秒杀抢购场景)", notes = "测试分布式锁(商品秒杀抢购场景)")
    @GetMapping("/reduce_inventory")
    public String reduceInventory() {

        //return normal();
        return distributedLock();
       // return "";
    }

    //普通操作
    private String normal() {
        String stockKey="good1";//redis里商品存量
        try {
            ValueOperations<String, String> vopt = redisTemplate.opsForValue();
            int stock =Integer.parseInt(vopt.get(stockKey));

            if(stock<=0){
                log.warn( TAG+"库存已经为0,购买失败!");
            }
            else {
                log.info( TAG+ "购买成功,最新库存:" + (--stock));
            }

            redisTemplate.opsForValue().set(stockKey,String.valueOf(stock));//更新存量

        } catch (Exception e) {
            log.warn( TAG+"异常:"+ Arrays.toString(e.getStackTrace()));
        }

        return "end";
    }

    /**
     * 使用分布式锁
     * @return
     */
    private String distributedLock() {
        String stockKey="good1";//redis里商品存量


        String lock="good_lock";//锁key,不管多少个应用服务,某一时刻只能卖一份订单
        String value= UUID.randomUUID().toString();



        //设置分布式锁,key是锁id,value是当前应用进程标识,10秒后自动删除.10秒内能把业务做完,则不会有任何问题
        boolean resulr=redisTemplate.opsForValue().setIfAbsent(lock,value,10, TimeUnit.SECONDS);

        if(!resulr){
            log.warn( TAG+"当前购买人数太多,请排队等候!");
            return "当前购买人数太多,请排队等候!";
        }

        try {
            ValueOperations<String, String> vopt = redisTemplate.opsForValue();
            int stock =Integer.parseInt(vopt.get(stockKey));

            if(stock<=0){
                 log.warn( TAG+"库存已经为0,购买失败!");
            }
            else {
                log.info( TAG+ "购买成功,最新库存:" + (--stock));
            }

            redisTemplate.opsForValue().set(stockKey,String.valueOf(stock));//更新存量

        } catch (Exception e) {
            log.warn( TAG+"异常:"+ Arrays.toString(e.getStackTrace()));
        }
        finally {
            ValueOperations<String, String> vopt = redisTemplate.opsForValue();
            //自己才能删除自己的锁
            if(value.equals(vopt.get(lock))){
                redisTemplate.delete(lock);//不论过程如何,最后把锁删掉
                log.warn(TAG + "释放分布式锁,value:" + value);
            }
        }

        return "end";
    }


}
