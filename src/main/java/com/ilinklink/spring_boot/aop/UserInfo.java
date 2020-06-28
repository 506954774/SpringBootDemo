package com.ilinklink.spring_boot.aop;

import java.io.Serializable;

import lombok.Data;

/**
 * UserInfo
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/6/28  18:00
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;
    private String userName;

}
