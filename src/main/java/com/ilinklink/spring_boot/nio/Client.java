package com.ilinklink.spring_boot.nio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Client
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/11/27 14:33
 * Copyright :  版权所有
 **/
public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9999);
            // 向服务端程序发送数据
            OutputStream ops = socket.getOutputStream();
            OutputStreamWriter opsw = new OutputStreamWriter(ops);
            BufferedWriter bw = new BufferedWriter(opsw);

            bw.write("hello world");
            bw.flush();

            Thread.sleep(Integer.MAX_VALUE);
        } catch ( Exception e) {

        }
    }
}
