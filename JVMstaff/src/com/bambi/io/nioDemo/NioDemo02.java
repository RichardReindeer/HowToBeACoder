package com.bambi.io.nioDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

/**
 * Nio 无阻塞io
 */
public class NioDemo02 {

    public static void main(String[] args) throws Exception{
        LinkedList<SocketChannel> clientChannels = new LinkedList<>();

        //获取ServerSocketChannel通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //设置端口号
        serverSocketChannel.bind(new InetSocketAddress(9000));
        System.out.println("服务器端创建成功");
        while (true){
            //获取客户端socket
            SocketChannel client = serverSocketChannel.accept();
            //将客户端设置为非阻塞

            if(client==null){
                System.out.println("没有客户端连接");
            }else {
                client.configureBlocking(false);
                clientChannels.add(client);
            }

            //将客户端内容读出来
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
            clientChannels.forEach(socketChannel -> {
                //尝试读取，根据返回值判断客户端是否发送信息
                try {
                    Integer num = socketChannel.read(byteBuffer);
                    if(num>0){
                        //读到信息则将byteBuffer改为写状态
                        byteBuffer.flip();
                        byte[] data = new byte[byteBuffer.limit()];
                        byteBuffer.get(data);

                        String message =  new String(data);
                        System.out.println("读取到的客户端信息:"+message);
                        byteBuffer.clear();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }


    }
}
