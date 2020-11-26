package com.ilinklink.spring_boot.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIOServer
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/11/26 12:15
 * Copyright :  版权所有
 **/
public class BIOServer {

    public static void main3(String[] args) {

        ExecutorService executorService= Executors.newFixedThreadPool(3);

        try {
            ServerSocket serverSocket=new ServerSocket(8888);
            RequestHandler requestHandler=new RequestHandler();
            System.out.println("BIOServer has started,listening on port:" + serverSocket.getLocalSocketAddress());

            while (true){//这里死循环,是为了不断监听数据
                System.out.println("进入第一层循环"  );
                Socket clientSocket=serverSocket.accept();//这行代码会阻塞住,下面的不会走,直到telnet那端连接进来
                System.out.println("Connection from:" + clientSocket.getRemoteSocketAddress());//连接进来之后,打印

                Scanner input= new Scanner(clientSocket.getInputStream());
                String request=input.nextLine();
                if("quit".equals(request)){
                    break;
                }
                System.out.println(String.format("当前线程id:%s ,From %s : %s", Thread.currentThread().getId(), clientSocket.getRemoteSocketAddress(), request));

                String response=requestHandler.handle(request);

                //走到下面这行代码,则本次循环完毕,准备进入下一次循环.刚刚开启的telnet端再输入数据则无法看到
                clientSocket.getOutputStream().write(response.getBytes());
            }

        } catch (Exception e) {

        }

    }

    public static void main (String[] args) {

        ExecutorService executorService= Executors.newFixedThreadPool(3);

        try {
            ServerSocket serverSocket=new ServerSocket(8888);
            RequestHandler requestHandler=new RequestHandler();
            System.out.println("BIOServer has started,listening on port:" + serverSocket.getLocalSocketAddress());

            while (true){//这里死循环,是为了不断监听数据
                System.out.println("进入第一层循环"  );

                Socket clientSocket=serverSocket.accept();//这行代码会阻塞住,下面的不会走,直到telnet那端连接进来


                System.out.println("Connection from:" + clientSocket.getRemoteSocketAddress());//打印

                //这里使用线程池,是为了不阻塞主线程,因为 socket.getInputStream()会阻塞掉线程
                executorService.submit(new ClientHandler(clientSocket, requestHandler));

                //然后会进入下一次循环.如果没有新的连接进来,则又会阻塞在accept那里
            }

        } catch (Exception e) {

        }

    }
    public static void main1(String[] args) {

        try {
            ServerSocket serverSocket=new ServerSocket(8888);

            System.out.println("BIOServer has started,listening on port:" + serverSocket.getLocalSocketAddress());

            while (true){
                Socket clientSocket=serverSocket.accept();

                System.out.println("Connection from:" + clientSocket.getRemoteSocketAddress());

                Scanner input= new Scanner(clientSocket.getInputStream());
                while (true){
                    String request=input.nextLine();
                    if("quit".equals(request)){
                        break;
                    }
                    System.out.println(String.format("From %s : %s",clientSocket.getRemoteSocketAddress(),request));

                    String response="From BIOServer Hello "+request+".\n";
                    clientSocket.getOutputStream().write(response.getBytes());

                }

            }

        } catch (Exception e) {

        }

    }
}
