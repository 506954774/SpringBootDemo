package com.ilinklink.spring_boot;

import com.ilinklink.spring_boot.exception.AdminException;
import com.ilinklink.spring_boot.model.Gps;
import com.ilinklink.spring_boot.service.HbaseService;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest(classes= Application .class)
@WebAppConfiguration
@EnableScheduling
@RunWith(SpringJUnit4ClassRunner.class)
public class HbaseTests {

    @Resource
    private HbaseService hbaseService;

    @Test
    public void inser() {

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
    public void queryDate() {


        try {
            TreeSet<String> gpsDateByDeviceId = hbaseService.getGpsDateByDeviceId("1");
            log.error("gpsDateByDeviceId:{},size:{}"+gpsDateByDeviceId,gpsDateByDeviceId.size());

        } catch (AdminException e) {
            log.error("error:"+e);
        }
    }

    @Test
    public  void queryGpsList() {

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
