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
@ApiModel("取redis")
public class RedisGetParams implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "键")
    private String key;


}
