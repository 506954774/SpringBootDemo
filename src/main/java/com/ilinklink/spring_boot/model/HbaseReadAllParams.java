package com.ilinklink.spring_boot.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * HbaseReadAllParams
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/4/24  10:27
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@Data
@ApiModel("查询参数: 表明,列簇")
public class HbaseReadAllParams implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "表名",required = true)
    private String tableName;


    @ApiModelProperty(value = "行名",required = true)
    private String rowName;


    @ApiModelProperty(value = "列簇名",required = true)
    private String familyName;
    @ApiModelProperty(value = "列",required = true)
    private String qualifier;


}
