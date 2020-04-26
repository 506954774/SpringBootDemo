package com.ilinklink.spring_boot.action;

import com.ilinklink.spring_boot.exception.AdminException;
import com.ilinklink.spring_boot.model.Gps;
import com.ilinklink.spring_boot.model.GpsQueryParams;
import com.ilinklink.spring_boot.model.HbaseReadAllParams;
import com.ilinklink.spring_boot.model.HbaseReadParams;
import com.ilinklink.spring_boot.service.HbaseService;
import com.ilinklink.spring_boot.service.MemberService;
import com.ilinklink.spring_boot.service.impl.MemberServiceImpl;
import com.ilinklink.spring_boot.web.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.ApiOperation;


/**
 * Created by Jason on 2019/11/4.
 */
@RestController
@RequestMapping("/hbase")
public class HbaseAction extends ServerExceptionHandler{

    @Autowired
    private HbaseService hbaseService;

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "测试读取hbase的数据",  response = String.class, notes = "测试读取hbase的数据")
    @PostMapping("/hbase_read")
    public ResponseEntity hBaseRead(HttpServletRequest request,@RequestBody HbaseReadAllParams params) {
        try {
            String result=memberService.getValueFromHbase(params.getTableName(),params.getRowName(),params.getFamilyName(),params.getQualifier());
            ResponseEntity<String> responseEntity = new ResponseEntity<>(true);
            responseEntity.setResult(result);
            return responseEntity;
        } catch (AdminException e) {
            return new ResponseEntity<>(e.getErrorCode(), false, e.getMessage());
        }
    }


    @ApiOperation(value = "按日期获取估计列表(日期列表)",  response = String.class, notes = "按日期获取估计列表(日期列表)")
    @PostMapping("/getTraces")
    public ResponseEntity hBaseRead( @RequestBody GpsQueryParams params) {
        try {
            ResponseEntity<TreeSet<String>> responseEntity = new ResponseEntity<>(true);
            responseEntity.setResult(hbaseService.getGpsDateByDeviceId(params.getDeviceId()));
            return responseEntity;
        } catch (AdminException e) {
            return new ResponseEntity<>(e.getErrorCode(), false, e.getMessage());
        }
    }

    @ApiOperation(value = "测试从hbase读取设备的轨迹列表",    notes = "测试从hbase读取设备的轨迹列表")
    @PostMapping("/getGpsByTime")
    public ResponseEntity hBaseWrite( @RequestBody GpsQueryParams params) {

        try {
            ResponseEntity<List<Gps>> responseEntity = new ResponseEntity<>(true);
            responseEntity.setResult(hbaseService.getGpsByTime(params.getDeviceId(),params.getStartTime(),params.getEndTime()));
            return responseEntity;
        } catch (AdminException e) {
            return new ResponseEntity<>(e.getErrorCode(), false, e.getMessage());
        }
    }
}
