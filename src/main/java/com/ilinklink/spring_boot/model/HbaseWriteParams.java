package com.ilinklink.spring_boot.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("hbase相关")
public class HbaseWriteParams implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "行名")
    private String row;
    @ApiModelProperty(value = "列簇标识 ")
    private String qualifier;
    @ApiModelProperty(value = "value")
    private String value;


}
