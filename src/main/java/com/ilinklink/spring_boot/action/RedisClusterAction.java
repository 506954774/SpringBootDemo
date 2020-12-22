package com.ilinklink.spring_boot.action;

import com.ilinklink.spring_boot.exception.AdminException;
import com.ilinklink.spring_boot.model.RedisGetParams;
import com.ilinklink.spring_boot.model.RedisSetParams;
import com.ilinklink.spring_boot.service.RedisClusterService;
import com.ilinklink.spring_boot.web.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import io.swagger.annotations.ApiOperation;


/**
 * Created by Jason on 2019/11/4.
 */
@RestController
@RequestMapping("/redis/cluster")
public class RedisClusterAction extends ServerExceptionHandler{


    @Resource
    private RedisClusterService redisClusterService;



    @ApiOperation(value = "测试redisCluster存", notes = "测试redisCluster存")
    @PostMapping("/redis_set")
    public ResponseEntity redisSet(@RequestBody RedisSetParams params) {
        try {
            redisClusterService.set(params.getKey(),params.getValue());
            return new ResponseEntity<>(true);
        } catch (Exception e) {
            return new ResponseEntity<>(" ", false, e.getMessage());

        }
    }

    @ApiOperation(value = "测试redisCluster取", notes = "测试redisCluster取", response = String.class)
    @PostMapping("/redis_get")
    public ResponseEntity redisGet(@RequestBody RedisGetParams params) {
        try {
            String value = redisClusterService.get(params.getKey());
            ResponseEntity<String> responseEntity = new ResponseEntity<>(true);
            responseEntity.setResult(value);
            return responseEntity;
        } catch (AdminException e) {
            return new ResponseEntity<>(" ", false, e.getMessage());


        }
    }




}
