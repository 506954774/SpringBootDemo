package com.ilinklink.spring_boot;

import lombok.Data;

/**
 * ThreadTest
 *
 *  使用wait和notify解决脏读,是线程按照我们想要的逻辑顺序去执行
 *  本例中,按写一条,读一条的顺序去执行,避免脏读
 *


 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/7/1  17:26
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
public class ConcurrencyTest {


    private static User mUser=new User();



    @Data
    public static class User{
        private String name;
        private String gender;
        private boolean isBeingWriten;



    }





   static class ThreadWrite extends Thread{

        public ThreadWrite() {
            setName("线程write");
        }

        @Override
        public void run() {


            synchronized (mUser){

                int count=0;
                while (true){

                    if(!mUser.isBeingWriten){//正在读,则等待
                        try {
                            mUser.wait();
                        } catch (InterruptedException e) {

                        }
                    }
                    if(count==0){
                        mUser.setName("杨过");
                        mUser.setGender("男");
                    }
                    else {
                        mUser.setName("小龙女");
                        mUser.setGender("女");
                    }
                    System.out.println(ThreadWrite.this.getName()+",写入:"+mUser.getName()+","+mUser.getGender());
                    count=(count+1)%2;

                    mUser.setBeingWriten(false);//把判断值,改为读取时需要的那个状态,并notify
                    mUser.notify();
                }
            }

        }
    }

    static class ThreadRead extends Thread{

        public ThreadRead( ) {
            setName("线程read");

        }

        @Override
        public void run() {

            synchronized (mUser){
                while (true){
                    if(mUser.isBeingWriten){//正在写,则等待
                        try {
                            mUser.wait();
                        } catch (InterruptedException e) {

                        }
                    }
                    System.out.println(ThreadRead.this.getName()+",读取结果:"+mUser.getName()+","+mUser.getGender());

                    mUser.setBeingWriten(true);//把状态给改为我们想要的逻辑,我们读完了,准备让代码去写
                    mUser.notify();
                }
            }


        }
    }



    public static void main(String[] args) {


        ThreadWrite threadWrite=new ThreadWrite( );
        ThreadRead threadRead=new ThreadRead( );

        threadWrite.start();
        threadRead.start();


    }
}
