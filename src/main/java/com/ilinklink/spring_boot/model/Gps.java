package com.ilinklink.spring_boot.model;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Jason on 2019/11/22.
 */
@Data
@ApiModel("设备行程轨迹vo对象")
public class Gps implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "时间", required = true)
    private long time;
    @ApiModelProperty(value = "经度", required = true)
    private double lon;
    @ApiModelProperty(value = "纬度", required = true)
    private double lat;
    @ApiModelProperty(value = "速度", required = true)
    private double speed;
}
