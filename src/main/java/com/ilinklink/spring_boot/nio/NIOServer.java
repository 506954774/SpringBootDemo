package com.ilinklink.spring_boot.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * NIOServer
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/11/27 14:08
 * Copyright :  版权所有
 **/
public class NIOServer {

    //todo 如果单独写一个socket客户端  Client,给NIO发数据,发现数据粘在一起了,粘包问题

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(9999));
        System.out.println("NIOServer has started,listening on port:" + serverSocketChannel.getLocalAddress());


        Selector selector=Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);

        RequestHandler requestHandler=new RequestHandler();

        while (true){
            int select=selector.select();
            if(select==0){
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();

                if(key.isAcceptable()){//key是acceptable,则说明监听到客户端接入了
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();//拿到serverChannel
                    SocketChannel clientChnnel = channel.accept();//accept后拿到客户端
                    System.out.println("Connection from:" + clientChnnel.getRemoteAddress());

                    clientChnnel.configureBlocking(false);//设置为非阻塞
                    clientChnnel.register(selector,SelectionKey.OP_READ);//状态改为开启读取
                }
                if(key.isReadable()){//key是可读,说明有数据过来了
                    byteBuffer.clear();
                    SocketChannel channel= (SocketChannel) key.channel();//拿到客户端的socket
                    channel.read(byteBuffer);//读到buffer里
                    String request=new String(byteBuffer.array()).trim();
                    byteBuffer.clear();
                    System.out.println(String.format("From %s : %s", channel.getRemoteAddress(), request));

                    String response=requestHandler.handle(request);
                    channel.write(ByteBuffer.wrap(response.getBytes()));//写一个出去
                }
                iterator.remove();//每次执行完了,把第一个删掉
            }

        }



    }
}
