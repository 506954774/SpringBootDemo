package com.ilinklink.spring_boot.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * HbaseInsertParams
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/4/23  12:01
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@Data
@ApiModel (" Hbase 插入参数")
public class HbaseInsertParams implements Serializable {

    private static final long serialVersionUID = 1L;



    @ApiModelProperty(value = " 行 名",required = true)
    private String rowName;


    @ApiModelProperty(value = " 列",required = true)
    private String qualifier;



    @ApiModelProperty(value = " value",required = true)
    private String value;
}
