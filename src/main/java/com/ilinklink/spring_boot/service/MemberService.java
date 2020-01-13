package com.ilinklink.spring_boot.service;

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
public interface MemberService {


    boolean exist(String memberId) throws AdminException;

    void redisSet(RedisSetParams params) throws AdminException;

    String redisGet(RedisGetParams params) throws AdminException;

    void jPush(JpushParams params) throws AdminException;

    /**
     * 文件上传
     * @param mFile
     * @return
     * @throws AdminException
     */
    String uploadFile(MultipartFile mFile) throws AdminException;

    /**
     * 单文件上传
     * @param request
     * @return
     * @throws AdminException
     */
    String uploadFile(HttpServletRequest request) throws AdminException;

    /**
     * 批量上传
     * @param request
     * @return
     * @throws AdminException
     */
    List<String>  multiImport(HttpServletRequest request)throws AdminException;

    void sendMQ()throws AdminException;

}
