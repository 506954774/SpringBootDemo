package com.ilinklink.spring_boot.mapper;

import com.ilinklink.spring_boot.aop.UserInfo;

import org.apache.ibatis.annotations.Param;

/**
 * Created by liaosj on 2019/11/11.
 */
public interface MemberMapper {


    /**
     * 查询会员是否存在
     *
     * @param memberId
     * @return
     */
    Integer queryMemberExist(@Param("memberId") String memberId);

    /**
     * 查询用户名
     * @param memberId
     * @return
     */
    UserInfo queryUser(@Param("memberId") String memberId);


}
