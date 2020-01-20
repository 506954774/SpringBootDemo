package com.ilinklink.spring_boot.hBase;


import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.data.hadoop.hbase.RowMapper;

import lombok.extern.slf4j.Slf4j;

import static com.ilinklink.spring_boot.ServerConstants.LOGGER_PREFIX;

/**
 * JsonRowMapper
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/1/19  10:15
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@Slf4j
public class JsonRowMapper implements RowMapper<String> {

    private static byte[] COLUMN_FAMILY = "cf".getBytes();
    private static byte[] NAME = "a".getBytes();

    private String mColumnFamily;
    private String mName;

    public JsonRowMapper(String mColumnFamily, String mName) {
        this.mColumnFamily = mColumnFamily;
        this.mName = mName;
    }

    @Override
    public String mapRow(Result result, int rowNum) throws Exception {
        //String reslut = Bytes.toString(result.getValue(COLUMN_FAMILY, NAME));
        if(mColumnFamily==null||mName==null){
            log.warn(LOGGER_PREFIX+"hbase, mapRow(Result result, int rowNum):参数不合法!");
            return null;
        }
        String reslut = Bytes.toString(result.getValue(mColumnFamily.getBytes(), mName.getBytes()));
        log.info(LOGGER_PREFIX+"hbase, mapRow(Result result, int rowNum):"+reslut);
        return reslut;
    }
}