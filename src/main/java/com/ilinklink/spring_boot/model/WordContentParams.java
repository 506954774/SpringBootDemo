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
@ApiModel ("word文字內容")
public class WordContentParams implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "文本內容",required = true)
    private String content;


}
