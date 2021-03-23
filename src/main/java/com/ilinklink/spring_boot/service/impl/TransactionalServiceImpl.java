package com.ilinklink.spring_boot.service.impl;
import java.security.AccessControlException;
import java.util.Date;

import com.ilinklink.spring_boot.aop.OrderVo;
import com.ilinklink.spring_boot.aop.UserInfo;
import com.ilinklink.spring_boot.aop.setValue.NeedSetVaule;
import com.ilinklink.spring_boot.exception.AdminException;
import com.ilinklink.spring_boot.mapper.MemberMapper;
import com.ilinklink.spring_boot.model.PtMemberInfo;
import com.ilinklink.spring_boot.service.AopService;

import com.ilinklink.spring_boot.service.TransactionalService;
import com.sun.corba.se.spi.activation.BadServerDefinition;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * TransactionalServiceImpl
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2019/11/28  19:43
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@Slf4j
@Service
public class TransactionalServiceImpl extends BaseService implements TransactionalService {


    @Resource
    private MemberMapper memberMapper;

    @Override
    @Transactional
    public void test4Callback() {

        PtMemberInfo member1=new PtMemberInfo();
        member1.setMemberId("no1");
        member1.setMemberUid("");
        member1.setAgentLevel(0);
        member1.setAgentLevelUtime(new Date());
        member1.setAgentProvince("");
        member1.setAgentCity("");
        member1.setAgentArea("");
        member1.setActiveCode("");
        member1.setHicoinRegister(0);
        member1.setInviteCode("");
        member1.setPayPwd("");
        member1.setPayPwdSalt("");
        member1.setPayPwdUtime(new Date());
        member1.setMemberAccount("");
        member1.setMemberPwd("");
        member1.setMemberPwdSalt("");
        member1.setPwdUtime(new Date());
        member1.setMemberNickname("");
        member1.setMemberGender(0);
        member1.setMemberAge(0);
        member1.setMemberAvatar("");
        member1.setMemberCouponBalance(0);
        member1.setBalanceUtime(new Date());
        member1.setFirstMember(0);
        member1.setAccStatus(0);
        member1.setAccStatusUtime(new Date());
        member1.setTradeStatus(0);
        member1.setTradeStatusUtime(new Date());
        member1.setMemberRemove(0);
        member1.setRemoveTime(new Date());
        member1.setCTime(new Date());
        member1.setUTime(new Date());
        memberMapper.insert(member1);

        UserInfo no1 = memberMapper.queryUser("no1");

        log.info("抛出异常之前,查一次第一条数据{}",no1);


        if(1==1){
            throw new RuntimeException("人为制造异常" );
        }


        PtMemberInfo member2=new PtMemberInfo();
        member2.setMemberId("no2");
        //member2.setMemberId("no11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        member2.setMemberUid("");
        member2.setAgentLevel(0);
        member2.setAgentLevelUtime(new Date());
        member2.setAgentProvince("");
        member2.setAgentCity("");
        member2.setAgentArea("");
        member2.setActiveCode("");
        member2.setHicoinRegister(0);
        member2.setInviteCode("");
        member2.setPayPwd("");
        member2.setPayPwdSalt("");
        member2.setPayPwdUtime(new Date());
        member2.setMemberAccount("");
        member2.setMemberPwd("");
        member2.setMemberPwdSalt("");
        member2.setPwdUtime(new Date());
        member2.setMemberNickname("");
        member2.setMemberGender(0);
        member2.setMemberAge(0);
        member2.setMemberAvatar("");
        member2.setMemberCouponBalance(0);
        member2.setBalanceUtime(new Date());
        member2.setFirstMember(0);
        member2.setAccStatus(0);
        member2.setAccStatusUtime(new Date());
        member2.setTradeStatus(0);
        member2.setTradeStatusUtime(new Date());
        member2.setMemberRemove(0);
        member2.setRemoveTime(new Date());
        member2.setCTime(new Date());
        member2.setUTime(new Date());
        memberMapper.insert(member2);

    }


}
