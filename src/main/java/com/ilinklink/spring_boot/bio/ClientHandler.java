package com.ilinklink.spring_boot.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * ClientHandler
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/11/26 12:47
 * Copyright :  版权所有
 **/
public class ClientHandler implements Runnable {

    private Socket clientSocket;

    private RequestHandler requestHandler;


    public ClientHandler(Socket socket, RequestHandler requestHandler) {
        this.clientSocket = socket;
        this.requestHandler = requestHandler;
    }

    @Override
    public void run() {
        try {
            while (true){
                Scanner input= new Scanner(clientSocket.getInputStream());
                String request=input.nextLine();
                if("quit".equals(request)){
                    break;
                }
                System.out.println(String.format("当前线程id:%s ,From %s : %s", Thread.currentThread().getId(), clientSocket.getRemoteSocketAddress(), request));

                String response=requestHandler.handle(request);

                clientSocket.getOutputStream().write(response.getBytes());

            }
        } catch (IOException e) {

        }

    }
}
