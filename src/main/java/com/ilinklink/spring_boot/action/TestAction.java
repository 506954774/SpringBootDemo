package com.ilinklink.spring_boot.action;

import com.ilinklink.spring_boot.exception.AdminException;
import com.ilinklink.spring_boot.model.JpushParams;
import com.ilinklink.spring_boot.model.QueryMemberParams;
import com.ilinklink.spring_boot.model.RedisGetParams;
import com.ilinklink.spring_boot.model.RedisSetParams;
import com.ilinklink.spring_boot.service.MemberService;
import com.ilinklink.spring_boot.web.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * Created by Jason on 2019/11/4.
 */
@RestController
@RequestMapping("/private")
public class TestAction extends ServerExceptionHandler{

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "测试swagger", notes = "测试swagger")
    @GetMapping("/test")
    public ResponseEntity sendByForgetPwd(@ApiParam(name = "tel", value = "手机号", required = true)
                                          @RequestParam(value = "tel") String tel) {
            return new ResponseEntity<>(true);

    }

    @ApiOperation(value = "查询用户是否存在", notes = "查询用户是否存在")
    @PostMapping("/query")
    public ResponseEntity query(@RequestBody QueryMemberParams params) {
        try {
            return new ResponseEntity<>(memberService.exist(params.getAccount()));
        } catch (AdminException e) {
            return new ResponseEntity<>(e.getErrorCode(), false, e.getMessage());
        }
    }

    @ApiOperation(value = "测试redis存", notes = "测试redis存")
    @PostMapping("/redis_set")
    public ResponseEntity redisSet(@RequestBody RedisSetParams params) {
        try {
            memberService.redisSet(params);
            return new ResponseEntity<>(true);
        } catch (AdminException e) {
            return new ResponseEntity<>(e.getErrorCode(), false, e.getMessage());

        }
    }

    @ApiOperation(value = "测试redis取", notes = "测试redis取", response = String.class)
    @PostMapping("/redis_get")
    public ResponseEntity redisGet(@RequestBody RedisGetParams params) {
        try {
            String value = memberService.redisGet(params);
            ResponseEntity<String> responseEntity = new ResponseEntity<>(true);
            responseEntity.setResult(value);
            return responseEntity;
        } catch (AdminException e) {
            return new ResponseEntity<>(e.getErrorCode(), false, e.getMessage());

        }
    }

    @ApiOperation(value = "测试极光推送", notes = "测试极光推送" )
    @PostMapping("/jiguang_push")
    public ResponseEntity push(@RequestBody JpushParams params) {
        try {
            memberService.jPush(params);
            return new ResponseEntity<>(true);
        } catch (AdminException e) {
            return new ResponseEntity<>(e.getErrorCode(), false, e.getMessage());
        }
    }

    @ApiOperation(value = "单文件上传", response = String.class, notes = "单文件上传,返回可访问的路径")
    @PostMapping("/upload")
    public ResponseEntity uploadFile(HttpServletRequest request) {
        try {
            String result=memberService.uploadFile(request);
            ResponseEntity<String> responseEntity = new ResponseEntity<>(true);
            responseEntity.setResult(result);
            return responseEntity;
        } catch (AdminException e) {
            return new ResponseEntity<>(e.getErrorCode(), false, e.getMessage());
        }
    }

    @ApiOperation(value = "多文件上传",  response = ArrayList.class, notes = "文件批量上传,返回list<String>")
    @PostMapping("/multi_upload")
    public ResponseEntity multiImport(HttpServletRequest request) {
        try {
            List<String> result=memberService.multiImport(request);
            ResponseEntity<List<String>> responseEntity = new ResponseEntity<>(true);
            responseEntity.setResult(result);
            return responseEntity;
        } catch (AdminException e) {
            return new ResponseEntity<>(e.getErrorCode(), false, e.getMessage());
        }
    }

    @ApiOperation(value = "测试发出消息到mq", notes = "测试发出消息到mq")
    @GetMapping("/sendMsg")
    public ResponseEntity sendByForgetPwd() {
        try {
            memberService.sendMQ();
            return new ResponseEntity<>(true);
        } catch (AdminException e) {
            return new ResponseEntity<>(e.getErrorCode(), false, e.getMessage());
        }
    }

}
