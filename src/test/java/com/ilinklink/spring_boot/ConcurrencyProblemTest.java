package com.ilinklink.spring_boot;

import lombok.Data;

/**
 * ThreadTest
 * 实验结果,会出现脏读,例如:
 *
 * 线程read,读取结果:杨过,男
 线程read,读取结果:杨过,女
 线程read,读取结果:小龙女,女
 线程read,读取结果:小龙女,男
 线程read,读取结果:小龙女,女
 线程read,读取结果:小龙女,男
 线程read,读取结果:小龙女,女
 线程read,读取结果:小龙女,女
 线程read,读取结果:杨过,女
 线程read,读取结果:杨过,男

 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/7/1  17:26
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
public class ConcurrencyProblemTest {


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
            int count=0;
            while (true){
                try {
                   // Thread.sleep(10);
                } catch (Exception e) {

                }
                if(count==0){
                    mUser.setName("杨过");
                    mUser.setGender("男");
                }
                else {
                    mUser.setName("小龙女");
                    mUser.setGender("女");
                }
                //System.out.println(ThreadWrite.this.getName()+",写入:"+mUser.getName()+","+mUser.getGender());

                count=(count+1)%2;
            }
        }
    }

    static class ThreadRead extends Thread{

        public ThreadRead( ) {
            setName("线程read");

        }

        @Override
        public void run() {
            while (true){
                try {
                   //Thread.sleep(10);
                } catch (Exception e) {

                }
               // System.out.println(ThreadRead.this.getName()+",读取结果:"+mUser.getName()+","+mUser.getGender());

                boolean wrong="杨过".equals(mUser.getName())&&"女".equals(mUser.getGender());
                if(wrong){
                    System.out.println(ThreadRead.this.getName()+",读取结果:"+mUser.getName()+","+mUser.getGender());

                }
               // System.out.println("杨过".equals(mUser.getName())&&"女".equals(mUser.getGender()));
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
