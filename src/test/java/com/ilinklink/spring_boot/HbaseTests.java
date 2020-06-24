package com.ilinklink.spring_boot;

import com.ilinklink.spring_boot.exception.AdminException;
import com.ilinklink.spring_boot.model.Gps;
import com.ilinklink.spring_boot.service.HbaseService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class HbaseTests {

    @Resource
    private HbaseService hbaseService;

    @Test
    void inser() {

        Random random=new Random();
        List<Gps> gpsList=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Gps gps=new Gps();
            gps.setLat(22.00+(0.0003*i));
            gps.setLon(123.00+(0.0003*i));
            gps.setTime(System.currentTimeMillis());
            gps.setSpeed(random.nextInt(10));
            gpsList.add(gps);
        }
        try {
            hbaseService.insertGps("1",gpsList);
        } catch (AdminException e) {
            log.error("error:"+e);
        }
    }

    @Test
    void queryDate() {


        try {
            TreeSet<String> gpsDateByDeviceId = hbaseService.getGpsDateByDeviceId("1");
            log.error("gpsDateByDeviceId:{},size:{}"+gpsDateByDeviceId,gpsDateByDeviceId.size());

        } catch (AdminException e) {
            log.error("error:"+e);
        }
    }

    @Test
    void queryGpsList() {

        //1587537966495
        //1587710766495

        try {
            Date now=new Date();
            long end=now.getTime();
            long start=end-(48*3600*1000);
            List<Gps> gpsDateByDeviceId = hbaseService.getGpsByTime("1",start,end);
            log.error("gpsDateByDeviceId:{},size:{}"+gpsDateByDeviceId,gpsDateByDeviceId.size());

        } catch (AdminException e) {
            log.error("error:"+e);
        }
    }

}
