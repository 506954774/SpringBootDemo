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
                    object.wait();//必须自己的同步代码块里，调用同步对象的wait，才能使当前线程停止，
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 10; i++) {
                    System.out.println( " T1 for循环，"+i);
                }

                System.out.println(System.currentTimeMillis()+":T1 end! ");
            }
        }
    }
    public static class T4 extends  Thread{
        public void run() {
            synchronized (object){

                System.out.println(System.currentTimeMillis()+":T4 start! ");
                System.out.println(System.currentTimeMillis()+":T4 wait for object");

                try {
                    object.wait();//必须自己的同步代码块里，调用同步对象的wait，才能使当前线程停止，
                } catch (Exception e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 10; i++) {
                    System.out.println( " T4 for循环，"+i);
                }

                System.out.println(System.currentTimeMillis()+":T4 end! ");
            }
        }
    }

    public static class T2 extends Thread{
        public void run() {
            synchronized (object){

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(System.currentTimeMillis()+":唤醒线程 start! notify one thread");


                try {
                    //object.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //object.notify();//一次只能唤醒一个线程，cup将随机唤醒一个正在wait的线程


                object.notifyAll();//全部唤醒



                System.out.println(System.currentTimeMillis()+":唤醒线程 end! ");

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
        Thread t4 = new T4();
        t1.start();
        t4.start();
        //t3.start();
        t2.start();


    }
}
