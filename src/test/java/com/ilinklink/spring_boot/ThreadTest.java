package com.ilinklink.spring_boot;

/**
 * ThreadTest
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/7/1  17:26
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
public class ThreadTest {

    final static Object object = new Object();

    public static class T1 extends  Thread{
        public void run() {
            synchronized (object){
                System.out.println(System.currentTimeMillis()+":T1 start! ");
                System.out.println(System.currentTimeMillis()+":T1 wait for object");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()+":T1 end! ");
            }
        }
    }

    public static class T2 extends Thread{
        public void run() {
            synchronized (object){
                System.out.println(System.currentTimeMillis()+":T2 start! notify one thread");
                object.notify();
                System.out.println(System.currentTimeMillis()+":T2 end! ");

                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class T3 extends  Thread{
        public void run() {
            synchronized (object){
                System.out.println(System.currentTimeMillis()+":T3 start! ");
                System.out.println(System.currentTimeMillis()+":T3 wait for object");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()+":T3 end! ");
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new T1();
        Thread t2 = new T2();
        Thread t3 = new T3();
        t1.start();
        t3.start();
        t2.start();


    }
}
