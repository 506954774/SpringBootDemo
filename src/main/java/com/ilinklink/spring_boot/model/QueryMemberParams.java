package com.ilinklink.spring_boot.model;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liaoshaojie
 * @creatdate 2019/9/27
 * @describe
 */
@Data
@ApiModel("查询矿机列表参数")
public class QueryMemberParams implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账号")
    private String account;


}
