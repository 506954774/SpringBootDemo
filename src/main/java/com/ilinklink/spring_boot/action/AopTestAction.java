package com.ilinklink.spring_boot.action;

import com.ilinklink.spring_boot.aop.OrderVo;
import com.ilinklink.spring_boot.exception.AdminException;
import com.ilinklink.spring_boot.model.Gps;
import com.ilinklink.spring_boot.model.GpsQueryParams;
import com.ilinklink.spring_boot.model.HbaseReadAllParams;
import com.ilinklink.spring_boot.service.AopService;
import com.ilinklink.spring_boot.service.HbaseService;
import com.ilinklink.spring_boot.service.MemberService;
import com.ilinklink.spring_boot.web.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * Created by Jason on 2019/11/4.
 */
@RestController
@RequestMapping("/aop")
public class AopTestAction extends ServerExceptionHandler{


    @Resource
    private AopService aopService;
    @ApiOperation(value = "测试aop", notes = "测试aop" ,response = OrderVo.class)
    @GetMapping("/test")
    public ResponseEntity sendByForgetPwd(@ApiParam(name = "tel", value = "手机号", required = true)
                                          @RequestParam(value = "tel") String tel) {


        try {
            ResponseEntity<OrderVo> responseEntity = new ResponseEntity<>(true);
            responseEntity.setResult(aopService.query());
            return responseEntity;
        } catch (AdminException e) {
            return new ResponseEntity<>(e.getErrorCode(), false, e.getMessage());
        }

    }




}
