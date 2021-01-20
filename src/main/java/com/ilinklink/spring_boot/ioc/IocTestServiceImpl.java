package com.ilinklink.spring_boot.ioc;

/**
 * IocTestServiceImpl
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2021/1/20  17:13
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
public class IocTestServiceImpl implements IocTestService{

    public IocTestServiceImpl() {
    }

    @Override
    public String test(String msg) {
        return "来自实现，返回一个字符串["+msg+"]";
    }
}
