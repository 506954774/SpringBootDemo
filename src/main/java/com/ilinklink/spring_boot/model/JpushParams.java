package com.ilinklink.spring_boot.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liaoshaojie
 * @creatdate 2019/9/27
 * @describe
 */
@Data
@ApiModel("推送参数")
public class JpushParams implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "极光id")
    private String registId;
    @ApiModelProperty(value = "推送标题")
    private String title;
    @ApiModelProperty(value = "推送内容")
    private String content;


}
