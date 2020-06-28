package com.ilinklink.spring_boot.service;

import com.ilinklink.spring_boot.aop.OrderVo;
import com.ilinklink.spring_boot.exception.AdminException;

/**
 * AopService
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/6/28  18:12
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
public interface AopService {

    OrderVo query() throws AdminException;
}
