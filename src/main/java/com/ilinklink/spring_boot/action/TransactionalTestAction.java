package com.ilinklink.spring_boot.action;

import com.ilinklink.spring_boot.service.TransactionalService;
import com.ilinklink.spring_boot.web.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 */
@RestController
@RequestMapping("/transactional_test")
public class TransactionalTestAction extends ServerExceptionHandler{

    @Autowired
    private TransactionalService transactionalService;

    @ApiOperation(value = "测试事务回滚", notes = "测试事务回滚")
    @GetMapping("/test")
    public ResponseEntity sendByForgetPwd(@ApiParam(name = "tel", value = "手机号", required = true)
                                          @RequestParam(value = "tel") String tel) {

             transactionalService.test4Callback();
             return new ResponseEntity<>(true);

    }



    // git config --global user.name "Chuck" $ git config --global user.email "506954774@qq.com"
}
