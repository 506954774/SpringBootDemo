package com.ilinklink.spring_boot.bio;

/**
 * RequestHandler
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/11/26 12:48
 * Copyright :  版权所有
 **/
public class RequestHandler {

    public String handle(String request){
       return  "From BIOServer Hello "+request+".\n";
    }
}
