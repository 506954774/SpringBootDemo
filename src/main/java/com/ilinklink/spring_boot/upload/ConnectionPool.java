package com.ilinklink.spring_boot.upload;

/**
 * Created by Jason on 2018/5/26.
 */

import org.csource.fastdfs.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class ConnectionPool {

    //最大连接数,可以写配置文件
    private int size = 5;
    //被使用的连接
    private ConcurrentHashMap<StorageClient1, Object> busyConnectionPool = null;
    //空闲的连接
    private ArrayBlockingQueue<StorageClient1> idleConnectionPool = null;

    private Object obj = new Object();

    private static ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getConnectionPool() {
        return instance;
    }

    //取出连接
    public StorageClient1 checkout(int waitTime) {
        StorageClient1 storageClient1 = null;
        try {
            storageClient1 = idleConnectionPool.poll(waitTime, TimeUnit.SECONDS);
            System.out.println(storageClient1);
            if (storageClient1 != null) {
                busyConnectionPool.put(storageClient1, obj);
            }
        } catch (InterruptedException e) {
            storageClient1 = null;
            e.printStackTrace();
        }
        return storageClient1;
    }

    //回收连接
    public void checkin(StorageClient1 storageClient1) {
        if (busyConnectionPool.remove(storageClient1) != null) {
            idleConnectionPool.add(storageClient1);
        }
    }

    //如果连接无效则抛弃，新建连接来补充到池里
    public void drop(StorageClient1 storageClient1) {
        if (busyConnectionPool.remove(storageClient1) != null) {
            TrackerServer trackerServer = null;
            TrackerClient trackerClient = new TrackerClient();
            try {
                trackerServer = trackerClient.getConnection();
                StorageClient1 newStorageClient1 = new StorageClient1(trackerServer, null);
                idleConnectionPool.add(newStorageClient1);
                System.out.println("------------------------- :connection +1");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (trackerServer != null) {
                    try {
                        trackerServer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //单例
    private ConnectionPool() {
        busyConnectionPool = new ConcurrentHashMap<StorageClient1, Object>();
        idleConnectionPool = new ArrayBlockingQueue<StorageClient1>(size);
        init(size);
    }

    //初始化连接池
    private void init(int size) {
        initClientGlobal();
        TrackerServer trackerServer = null;
        try {
            TrackerClient trackerClient = new TrackerClient();
            //只需要一个tracker server连接
            trackerServer = trackerClient.getConnection();
            StorageServer storageServer = null;
            StorageClient1 storageClient1 = null;
            for (int i = 0; i < size; i++) {
                storageClient1 = new StorageClient1(trackerServer, storageServer);
                idleConnectionPool.add(storageClient1);
                System.out.println("------------------------- :connection +1");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (trackerServer != null) {
                try {
                    trackerServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //初始化客户端
    private void initClientGlobal() {
        //连接超时时间
        ClientGlobal.setG_connect_timeout(2000);
        //网络超时时间
        ClientGlobal.setG_network_timeout(3000);
        ClientGlobal.setG_anti_steal_token(false);
        // 字符集
        ClientGlobal.setG_charset("UTF-8");
        ClientGlobal.setG_secret_key(null);
        // HTTP访问服务的端口号
        ClientGlobal.setG_tracker_http_port(8080);

        InetSocketAddress[] trackerServers = new InetSocketAddress[2];
        trackerServers[0] = new InetSocketAddress("10.64.2.171", 22122);
        trackerServers[1] = new InetSocketAddress("10.64.2.172", 22122);
        TrackerGroup trackerGroup = new TrackerGroup(trackerServers);
        //tracker server 集群
        ClientGlobal.setG_tracker_group(trackerGroup);
    }


}
