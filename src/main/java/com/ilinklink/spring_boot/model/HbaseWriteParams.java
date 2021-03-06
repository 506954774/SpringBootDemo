package com.ilinklink.spring_boot.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(" hbase 写入相关")
public class HbaseWriteParams implements Serializable {

    private static final long serialVersionUID = 1L;



    @ApiModelProperty(value = "表名",required = true)
    private String tableName;


    @ApiModelProperty(value = "行名",required = true)
    private String rowName;


    @ApiModelProperty(value = "列簇名",required = true)
    private String familyName;
    @ApiModelProperty(value = "列",required = true)
    private String qualifier;



    @ApiModelProperty(value = "value",required = true)
    private String value;


}
