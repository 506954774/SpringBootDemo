/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilinklink.spring_boot.web;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author shawn
 * @version 1.0.0
 * @datetime 2017-8-29 10:32:14
 * @copyright Shenzhen LingLing Technology Co., Ltd.
 */
public class AccessAllowInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = Logger.getLogger(AccessAllowInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
       /* //检查是否登录
        Account account = (Account) session.getAttribute(ServerConstants.SESSION_ATTR_ACCOUNT);
        logger.info(ServerConstants.LOGGER_PREFIX + "session用户信息[" + account + "], 请求接口url[" + request.getRequestURI() + "].");
        if (account == null) {
            response.setHeader("SESSIONSTATUS", "TIMEOUT");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            throw new AuthenticatingException();
        }*/

        // 检查是否有资源访问权限 TODO

        return true;
    }
}