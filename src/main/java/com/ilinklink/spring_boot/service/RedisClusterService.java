package com.ilinklink.spring_boot.service;

import com.ilinklink.spring_boot.aop.UserInfo;
import com.ilinklink.spring_boot.exception.AdminException;
import com.ilinklink.spring_boot.model.JpushParams;
import com.ilinklink.spring_boot.model.RedisGetParams;
import com.ilinklink.spring_boot.model.RedisSetParams;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * MemberService
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2019/11/28  19:43
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
public interface RedisClusterService {


    void set(String key,String vaule)throws AdminException;
    String get(String key)throws AdminException;
}
