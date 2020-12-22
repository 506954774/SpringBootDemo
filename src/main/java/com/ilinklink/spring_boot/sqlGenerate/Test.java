package com.ilinklink.spring_boot.sqlGenerate;


/**
 * Test
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/12/16  16:08
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
public class Test {

    private static String url = "jdbc:mysql://39.107.40.76:3306/retire?autoReconnect=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String userName = "admin";
    private static String userPwd = "123456";

    // 待生成的表名，注意是覆盖更新
    private static String[] tableNames;
    //表名，一次可以设置多个。表字段全部小写，下划线隔开！
    static{
        tableNames = new String[]{
                "pt_system_param",
        };
    }

    public static void main2(String[] args) {


       /* Config config=new Config();
        config.setDaoPackage("com.ilinklink.spring_boot.sqlGenerate.dao");
        config.setUrl(url);
        config.setDriverName(driverName);
        config.setUserName(userName);
        config.setUserPwd(userPwd);
        config.setTableNames(tableNames);

        GeneratorStarter.init(config);*/


    }
}
