package com.ilinklink.spring_boot.service;

import com.ilinklink.spring_boot.exception.AdminException;
import com.ilinklink.spring_boot.model.SeckillParams;

/**
 * MemberService
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2019/11/28  19:43
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
public interface RedisLockService {


    /**
     * 测试秒杀
     * @param params
     * @return
     * @throws AdminException
     */
    String seckill(SeckillParams params)throws AdminException;

}
