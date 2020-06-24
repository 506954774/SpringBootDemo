package com.ilinklink.spring_boot.service.impl;

import com.alibaba.fastjson.JSON;
import com.ilinklink.spring_boot.exception.AdminException;
import com.ilinklink.spring_boot.model.Gps;
import com.ilinklink.spring_boot.service.HbaseService;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.ResultsExtractor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import lombok.extern.slf4j.Slf4j;

import static com.ilinklink.spring_boot.ServerConstants.LOGGER_PREFIX;

/**
 * HbaseServiceImpl
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/4/23  16:24
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
@Slf4j
@Service
public class HbaseServiceImpl implements HbaseService {


    public static final String TABLE = "deviceGps";
    public static final String COLUMN_FAMILY = "gpsInfo";
    public static final String QUALIFIER = "gps";

    @Autowired
    private HbaseTemplate hbaseTemplate;


    @Override
    public TreeSet<String> getGpsDateByDeviceId(String deviceId) throws AdminException {

        final TreeSet<String> dates=new TreeSet<>();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");

        try {
            Scan a = new Scan();
            a.addFamily(Bytes.toBytes(COLUMN_FAMILY));
            a.setStartRow(Bytes.toBytes(deviceId));//以设备id打头的行,查出来
            ResultsExtractor<TreeSet<String>> action=new ResultsExtractor<TreeSet<String>>() {
                @Override
                public TreeSet<String>  extractData(ResultScanner resultScanner) throws Exception {

                    Iterator<Result> iterator = resultScanner.iterator();

                    while (iterator.hasNext()){
                        Result next = iterator.next();
                        byte[] row = next.getRow();
                        String rowName= Bytes.toString(row);

                        if(rowName.contains("$")){
                            try {
                                String date=rowName.split("[$]")[1];
                                String time =format.format(new Date(Long.parseLong(date)));
                                dates.add(time);
                            } catch ( Exception e) {
                                log.error(LOGGER_PREFIX+"读取日期,操作异常!", e);
                            }
                        }
                    }

                    return dates;
                }
            };
            hbaseTemplate.find(TABLE,a,action);

        } catch (Exception e) {
            log.error(LOGGER_PREFIX+"读取hbase,操作异常!", e);

        }

        return dates;
    }

    @Override
    public List<Gps> getGpsByTime(String deviceId, long startTime, long endTime) throws AdminException {


        final ArrayList<Gps> dates=new ArrayList<>();

        try {
            Scan a = new Scan();
            a.addFamily(Bytes.toBytes(COLUMN_FAMILY));
            a.withStartRow(Bytes.toBytes(deviceId+"$"+startTime));
            a.withStopRow(Bytes.toBytes(deviceId+"$"+endTime));
            ResultsExtractor<ArrayList<Gps>> action=new ResultsExtractor<ArrayList<Gps>>() {
                @Override
                public ArrayList<Gps>  extractData(ResultScanner resultScanner) throws Exception {



                    Iterator<Result> iterator = resultScanner.iterator();

                    while (iterator.hasNext()){

                        Result next = iterator.next();
                        byte[] value = next.getValue(COLUMN_FAMILY.getBytes(), QUALIFIER.getBytes());
                        String json =  Bytes.toString(value);

                        byte[] row = next.getRow();
                        String rowName= Bytes.toString(row);

                        if(rowName.contains("$")){
                            try {
                                Gps gps=JSON.parseObject(json,Gps.class);
                                dates.add(gps);
                            } catch ( Exception e) {
                                log.error(LOGGER_PREFIX+"解析数据,操作异常!", e);
                            }
                        }
                    }

                    return dates;
                }
            };
            hbaseTemplate.find(TABLE,a,action);

        } catch (Exception e) {
            log.error(LOGGER_PREFIX+"读取hbase,操作异常!", e);

        }

        return dates;

    }

    @Override
    public void insertGps(String deviceId, List<Gps> gps) throws AdminException {
        try {

            //单个for循环插入
           /* if(gps!=null&&gps.size()>0){
                for ( Gps gpsBean : gps){
                    String rowKey=deviceId+"$"+System.currentTimeMillis();
                    String json= JSON.toJSONString(gpsBean);
                    hbaseTemplate.put(TABLE,rowKey,COLUMN_FAMILY,QUALIFIER,json.getBytes());
                }
            }*/


           //批量插入
            batchPut(deviceId,gps);


        } catch (Exception e) {
            log.error(LOGGER_PREFIX+"插入hbase,操作异常!", e);
        }
    }





    @Value("${hbase.zookeeper.quorum}")
    private String quorum;
    @Value("${hbase.zookeeper.property.clientPort}")
    private String clientPort;
    @Value("${zookeeper.znode.parent}")
    private String parent;
    private void batchPut(String deviceId, List<Gps> gps) throws Exception {

        Configuration configuration = new Configuration();
        configuration.set("hbase.zookeeper.quorum", quorum);
        configuration.set("hbase.zookeeper.property.clientPort", clientPort);
        configuration.set("zookeeper.znode.parent", parent);


        Connection conn = ConnectionFactory.createConnection(configuration);
        HTable table = (HTable) conn.getTable(TableName.valueOf(TABLE));
        //关闭自动清理缓冲区
        table.setAutoFlushTo(false);

        List<Put> list = new LinkedList<Put>();
        if(gps!=null&&gps.size()>0){

            for ( Gps gpsBean : gps){

                //rowKey规则:    deviceId+"$"+时间毫秒数
                String rowKey=deviceId+"$"+System.currentTimeMillis();
                //rowKey="1test";
                log.info("rowkey:{}",rowKey);

                Thread.sleep(1);//休眠一毫秒,避免rowKey重复.实际情况不必如此

                String json= JSON.toJSONString(gpsBean);
                log.info("json:{}",json);

                Put put=new Put(Bytes.toBytes(rowKey));
                put.setWriteToWAL(false);//hbase端不打印日志,但是这样做出问题后无法修复数据
                put.addColumn(Bytes.toBytes(COLUMN_FAMILY),Bytes.toBytes(QUALIFIER),Bytes.toBytes(json));

                list.add(put);
            }

        }

        long time=System.currentTimeMillis();

        table.put(list);

        //清理提交

        table.flushCommits();
        table.close();
        conn.close();

        log.error("{}条数据,耗时:{}",gps.size(),System.currentTimeMillis()-time);

    }



}
