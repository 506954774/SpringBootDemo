package com.ilinklink.spring_boot.service;

import com.ilinklink.spring_boot.exception.AdminException;
import com.ilinklink.spring_boot.model.Gps;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * HbaseService
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/4/23  16:19
 * Copyright : 2014-2015 深圳掌通宝科技有限公司-版权所有
 */
public interface HbaseService {

    /**
     * 根据设备id,查询有轨迹的日期集合
     * 'yyyy-MM-dd'
     * @param deviceId
     * @return
     * @throws AdminException
     */
    TreeSet<String> getGpsDateByDeviceId(String deviceId)throws AdminException;

    /**
     * 查询时间段内的轨迹
     * @param deviceId
     * @param startTime
     * @param endTime
     * @return
     * @throws AdminException
     */
    List<Gps>  getGpsByTime(String deviceId,long startTime,long endTime)throws AdminException;

    /**
     * 插入gps集合
     * @param deviceId
     * @param gps
     * @throws AdminException
     */
    void insertGps(String deviceId,List<Gps> gps)throws AdminException;
}
