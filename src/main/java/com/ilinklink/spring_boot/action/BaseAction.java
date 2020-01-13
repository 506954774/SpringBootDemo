/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilinklink.spring_boot.action;




import com.ilinklink.spring_boot.ServerConstants;
import com.ilinklink.spring_boot.web.StringEscapeEditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shawn
 * @version 1.0.0
 * @datetime 2017-11-3 20:34:53
 * @copyright Shenzhen LingLing Technology Co., Ltd.
 */
public class BaseAction {

    @Autowired
    private MessageSource messageSource;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringEscapeEditor(true, true, true));
    }

    /**
     * code locale message.
     *
     * @param code
     * @return
     * @see {@code #getMessage(code, objects)}
     */
    public String getMessage(String code) {
        return getMessage(code, (Object) null);
    }

    /**
     * code locale message with parameters.
     *
     * @param code
     * @param objects
     * @return
     */
    public String getMessage(String code, Object... objects) {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            return messageSource.getMessage(code, objects, locale);
        } catch (Exception e) {
            return messageSource.getMessage(code, objects, new Locale("en", "US"));
        }
    }

    /**
     * code locale message.
     *
     * @param request
     * @param code
     * @return
     * @see {@code #getMessage(code, objects)}
     */
    public String getMessage(HttpServletRequest request, String code) {
        return getMessage(request, code, (Object) null);
    }

    /**
     * code locale message with parameters.
     *
     * @param request
     * @param code
     * @param objects
     * @return
     */
    public String getMessage(HttpServletRequest request, String code, Object... objects) {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            return messageSource.getMessage(code, objects, locale);
        } catch (Exception e) {
            return messageSource.getMessage(code, objects, new Locale("en", "US"));
        }
    }



    /**
     * 获取session中的图形验证码
     *
     * @param request
     * @return
     */
    public String getVerifyCode(HttpServletRequest request) {
        Object attrVal = request.getSession().getAttribute(ServerConstants.SESSION_ATTR_VERIFY_CODE);
        if (attrVal != null && attrVal instanceof String) {
            return (String) attrVal;
        }
        return null;
    }


}