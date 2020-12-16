package com.ilinklink.spring_boot;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadPoolTest
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/12/15  16:14
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
public class ThreadPoolTest {


    public static void main(String[] args) {



        try {
            ExecutorService executorService = Executors.newFixedThreadPool(2);
            executorService.execute(new Runnable() {
                @Override
                public void run() {

                    while (true){

                        try {
                            Thread.sleep(1000l);
                            System.out.println("线程1，执行");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            Thread.sleep(1000l);
                            System.out.println("线程2，执行");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
