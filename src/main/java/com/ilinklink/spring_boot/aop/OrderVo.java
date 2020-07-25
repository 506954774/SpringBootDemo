package com.ilinklink.spring_boot.aop;

import com.ilinklink.spring_boot.aop.setValue.SetVaule;
import com.ilinklink.spring_boot.service.MemberService;

import java.io.Serializable;

import lombok.Data;

/**
 * OrderVo
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/6/28  17:47
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@Data
public class OrderVo  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderId;

    private String userId;

    @SetVaule(beanClass = MemberService.class,method = "queryUser",paramField = "userId",targetField = "userName")
    private String custmerName;



}
