package com.ilinklink.spring_boot.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * SeckillParams
 * 责任人:  ChenLei
 * 修改人： ChenLei
 * 创建/修改时间: 2021/3/26 16:33
 * Copyright :  版权所有
 **/
@Data
@ApiModel(value="商品秒杀,参数")
public class SeckillParams  implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品id")
    private String goodsSkuId;
}
