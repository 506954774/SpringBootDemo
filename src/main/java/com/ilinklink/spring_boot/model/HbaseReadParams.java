package com.ilinklink.spring_boot.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("hbase相关")
public class HbaseReadParams implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "表名")
    private String table;
    @ApiModelProperty(value = "行名")
    private String row;
    @ApiModelProperty(value = "列簇名")
    private String cf;
    @ApiModelProperty(value = "key")
    private String key;


}
