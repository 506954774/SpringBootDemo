/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilinklink.spring_boot.exception;

/**
 * <p>
 *  服务响应码定义</p>
 *
 * @author shawn
 * @datetime 2017-9-1 16:04:39
 * @version 1.0.0
 * @copyright Shenzhen LingLing Technology Co., Ltd.
 */
public interface AdminErrorCode {

    /**
     * 请求成功
     */
    String REQUEST_SUCCESS = "000000";
    /**
     * 请求失败
     */
    String REQUEST_FAIL = "000001";
    /**
     * 请求异常
     */
    String REQUEST_EXCEPTION = "000002";
    /**
     * 登录失效
     */
    String SESSION_REQUIRED = "000003";
    /**
     * 访问权限不足
     */
    String PERMISSION_REQUIRED = "000004";

    /**
     * 请求参数为空
     */
    String PARAMS_EMPTY = "001000";
    /**
     * 登录账号为空
     */
    String LOGIN_ACCOUNT_EMPTY = "001001";
    /**
     * 登录密码为空
     */
    String LOGIN_PASSWORD_EMPTY = "001002";
    /**
     * 账号不存在
     */
    String LOGIN_ACCOUNT_NOT_EXIST = "001003";
    /**
     * 账号或密码输入有误
     */
    String LOGIN_ACCOUNT_PASSWORD_ERROR = "001004";
    /**
     * 账号处于冻结状态
     */
    String LOGIN_ACCOUNT_FROZEN = "001005";
    /**
     * 账号处于异常状态
     */
    String LOGIN_ACCOUNT_EXCEPTION = "001006";
    /**
     * 图形验证码输入有误
     */
    String LOGIN_VERIFY_CODE_ERROR = "001007";
    /**
     * 上传内容为空
     */
    String UPLOAD_CONTENT_EMPTY = "00100a";
    /**
     * 文件内容过大
     */
    String FILE_TOO_LARGE = "001008";
    /**
     * 文件类型不合法
     */
    String FILE_TYPE_INVALID = "001009";
    /**
     * 手机号已注册
     */
    String TEL_ALREADY_REGISTERED = "001010";
    /**
     * 手机号未注册
     */
    String TEL_NOT_REGISTERED = "001011";
    /**
     * 短信验证码已过期
     */
    String SMS_CODE_ALREADY_EXPIRE = "001012";
    /**
     * 短信验证码输入有误
     */
    String SMS_CODE_IS_WRONG = "001013";
    /**
     * 注册账号为空
     */
    String REGISTER_ACCOUNT_EMPTY = "001014";
    /**
     * 注册密码为空
     */
    String REGISTER_PASSWORD_EMPTY = "001015";
    /**
     * 短信验证码为空
     */
    String SMS_CODE_EMPTY = "001016";
    /**
     * 注册密码长度不能小于6位
     */
    String REGISTER_PASSWORD_LENGTH_INVALID = "001017";
    /**
     * 旧密码不能为空
     */
    String OLD_PASSWORD_EMPTY = "001018";
    /**
     * 新密码不能为空
     */
    String NEW_PASSWORD_EMPTY = "001019";
    /**
     * 用户不存在
     */
    String USER_NOT_EXIST = "001020";
    /**
     * 旧密码输入不正确
     */
    String OLD_PASSWORD_IS_WRONG = "001021";
    /**
     * 手机号已被用户绑定
     */
    String TEL_ALREADY_BIND = "001022";
    /**
     * 手机号不合法
     */
    String TEL_INVALID = "001023";
    /**
     * 图形验证码为空
     */
    String LOGIN_VERIFY_CODE_EMPTY = "001024";
    /**
     * 手机号为空
     */
    String TEL_EMPTY = "001025";
    /**
     * 服务类型为空
     */
    String ORDER_SERVICE_EMPTY = "001026";
    /**
     * 金额设置不合法
     */
    String ORDER_AMOUNT_INVALID = "001027";
    /**
     * 门店ID为空
     */
    String STORE_ID_EMPTY = "001028";
    /**
     * 门店名称已存在
     */
    String STORE_NAME_ALREADY_EXIST = "001029";
    /**
     * 时间段传入不合法
     */
    String QUERY_DAYS_INVALID = "001030";
    /**
     * 自定义时间段不合法
     */
    String QUERY_TIME_INVALID = "001031";
    /**
     * 重新新密码不能为空
     */
    String RE_NEW_PASSWORD_EMPTY = "001032";
    /**
     * 新密码输入不一致
     */
    String NEW_PASSWORD_NOT_MATCH = "001033";
    /**
     * 账号信息不存在
     */
    String ACCOUNT_INFO_NOT_EXIST = "001034";
    /**
     * 旧密码输入有误
     */
    String OLD_PWD_WRONG = "001035";
    /**
     * 账号名称已存在
     */
    String CUST_ACCOUNT_ALREADY_EXIST = "001036";
    /**
     * 角色名已存在
     */
    String ROLE_NAME_ALREADY_EXIST = "001037";
    /**
     * 订单不存在
     */
    String ORDER_NOT_EXIST = "001038";
    /**
     * 物流公司名称不可为空
     */
    String COMP_NAME_IS_NULL = "001039";
    /**
     * 物流单号不可为空
     */
    String DELIVER_NO_IS_NULL = "001040";
    /**
     * 订单id不可为空
     */
    String ORDER_ID_CAN_NOT_BE_NULL = "001041";
    /**
     * 会员id不可为空
     */
    String MEMBER_ID_CAN_NOT_BE_NULL = "001042";
    /**
     * 设置等级不能低于当前用户代理等级
     */
    String CANNOT_BELOW_CUR_LEVEL = "001043";
}
