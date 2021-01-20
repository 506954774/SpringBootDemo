package com.ilinklink.spring_boot.action;

import com.ilinklink.spring_boot.exception.AdminException;
import com.ilinklink.spring_boot.ioc.IocTestService;
import com.ilinklink.spring_boot.model.HbaseReadParams;
import com.ilinklink.spring_boot.model.JpushParams;
import com.ilinklink.spring_boot.model.QueryMemberParams;
import com.ilinklink.spring_boot.model.RedisGetParams;
import com.ilinklink.spring_boot.model.RedisSetParams;
import com.ilinklink.spring_boot.service.MemberService;
import com.ilinklink.spring_boot.service.impl.MemberServiceImpl;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * Created by Jason on 2019/11/4.
 */
@RestController
@RequestMapping("/ioc")
public class IocTestAction extends ServerExceptionHandler{



    @Resource
    private IocTestService iocTestService;

    @ApiOperation(value = "测试手动注入bean到ioc容器", notes = "测试手动注入bean到ioc容器")
    @GetMapping("/test")
    public ResponseEntity sendByForgetPwd(@ApiParam(name = "msg", value = "msg", required = true)
                                          @RequestParam(value = "msg") String msg) {

            String result=iocTestService.test(msg);
            ResponseEntity t= new ResponseEntity<String>(true);
            t.setResult(result);
            return t;

    }


}
