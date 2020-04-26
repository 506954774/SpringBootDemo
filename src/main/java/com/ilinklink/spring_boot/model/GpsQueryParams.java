package com.ilinklink.spring_boot.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * GpsQueryParams
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/4/23  17:39
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@Data
@ApiModel ("pgs 查询参数")
public class GpsQueryParams implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "设备id",required = true)
    private String deviceId;


    @ApiModelProperty(value = "startTime",required = true)
    private long startTime;

    @ApiModelProperty(value = "endTime",required = true)
    private long endTime;
}
