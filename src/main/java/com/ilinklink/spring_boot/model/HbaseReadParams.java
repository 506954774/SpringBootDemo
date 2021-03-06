package com.ilinklink.spring_boot.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("hbase相关")
public class HbaseReadParams implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = " 行 名",required = true)
    private String rowName;


    @ApiModelProperty(value = " 列",required = true)
    private String qualifier;




}
