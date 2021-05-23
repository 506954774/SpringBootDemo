package com.ilinklink.spring_boot.action;

import com.ilinklink.spring_boot.config.test.TestBean;
import com.ilinklink.spring_boot.exception.AdminException;
import com.ilinklink.spring_boot.model.*;
import com.ilinklink.spring_boot.service.MemberService;
import com.ilinklink.spring_boot.service.impl.MemberServiceImpl;
import com.ilinklink.spring_boot.web.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jason on 2019/11/4.
 */
@RestController
@RequestMapping("/test/auto_configration")
public class TestAutoConfigrationAction extends ServerExceptionHandler{

    @Autowired
    private TestBean testBean;

    @ApiOperation(value = "测试auto_configration", notes = "测试auto_configration")
    @GetMapping("/test")
    public ResponseEntity sendByForgetPwd(@ApiParam(name = "tel", value = "手机号", required = true)
                                          @RequestParam(value = "tel") String tel) {
        ResponseEntity result= new ResponseEntity<String>(true);
        result.setResult(testBean.test());
        return result;
    }


}
