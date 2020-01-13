/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilinklink.spring_boot;

/**
 * @author shawn
 * @version 1.0.0
 * @datetime 2017-8-29 10:20:49
 * @copyright Shenzhen LingLing Technology Co., Ltd.
 */
public interface ServerConstants {

    /**
     * logger format
     */
    String LOGGER_PREFIX = "[测试系统后台服务]";
    String SESSION_ATTR_VERIFY_CODE = "VERIFY_CODE";

    String SESSION_ATTR_ACCOUNT = "ACCOUNT";

    String PATH_ACCOUNT_ROOT = "/test/account";
    String PATH_FILE_ROOT = "/test/file";
    String PATH_COM_PARAM_ROOT = "/test/com/param";
}
