package com.ilinklink.spring_boot.service.impl;

import com.ilinklink.spring_boot.ServerConstants;
import com.ilinklink.spring_boot.aop.OrderVo;
import com.ilinklink.spring_boot.aop.UserInfo;
import com.ilinklink.spring_boot.aop.setValue.NeedSetVaule;
import com.ilinklink.spring_boot.exception.AdminErrorCode;
import com.ilinklink.spring_boot.exception.AdminException;
import com.ilinklink.spring_boot.hBase.JsonRowMapper;
import com.ilinklink.spring_boot.mapper.MemberMapper;
import com.ilinklink.spring_boot.model.JpushParams;
import com.ilinklink.spring_boot.model.RedisGetParams;
import com.ilinklink.spring_boot.model.RedisSetParams;
import com.ilinklink.spring_boot.rabbitMQ.MsgProducer;
import com.ilinklink.spring_boot.service.AopService;
import com.ilinklink.spring_boot.service.MemberService;
import com.ilinklink.spring_boot.upload.FastDFSClient;
import com.ilinklink.spring_boot.utils.RedisUtil;
import com.ilinklink.spring_boot.web.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import lombok.extern.slf4j.Slf4j;

import static com.ilinklink.spring_boot.ServerConstants.LOGGER_PREFIX;

/**
 * MemberServiceImpl
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2019/11/28  19:43
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@Slf4j
@Service
public class AopServiceImpl extends BaseService implements AopService {

/*
    @Resource
    private MemberMapper memberMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private  MsgProducer MsgProducer;

    @Autowired
    private HbaseTemplate hbaseTemplate;*/


    @Resource
    private MemberMapper memberMapper;

    @Override
    @NeedSetVaule
    public OrderVo query() throws AdminException {

        log.error("user:{}",memberMapper.queryUser("15262592514"));

        OrderVo vo=new OrderVo();
        
        vo.setOrderId("1");
        vo.setUserId("15262592514");
        //vo.setCustmerName(""); //这一步不手动做，使用aop实现：
        // 框架在这之后，会拿到注解里的dao层bean（MemberService），参数(userId),调用查询user的方法，并赋值给custmerName字段

        return vo;
    }

    @Override
    @NeedSetVaule
    public List<OrderVo> queryOrders() throws AdminException {

        ArrayList<OrderVo> result=new ArrayList<>();


        String[] ids=new String[]{"13037149452","15262592514"};

        for (int i = 0; i < 5; i++) {
            OrderVo vo=new OrderVo();
            vo.setOrderId("1");
            vo.setUserId(ids[i%2]);

            result.add(vo);
        }
        //vo.setCustmerName(""); //这一步不手动做，使用aop实现：
        // 框架在这之后，会拿到注解里的dao层bean（MemberService），参数(userId),调用查询user的方法，并赋值给custmerName字段

        return result;
    }
}
