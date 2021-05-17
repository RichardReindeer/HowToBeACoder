package com.bambi.io.guigu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {
    public static void main(String[] args) throws IOException {
        //得到一个网络通道
        SocketChannel client = SocketChannel.open();
        client.configureBlocking(false);

        //提供服务器端的ip和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost",8080);
        //连接服务器
        //如果没有连接上服务器
        if(!client.connect(inetSocketAddress)){

            //给予循环连接的尝试
            while (!client.finishConnect()){
                System.out.println("因为连接需要时间，客户端不会阻塞，可以做其他工作");
            }
        }

        //如果连接成功，就发送数据
        String str = "这是客户端发送的数据";

        //wraps a byte array into a buffer , 创建一个跟传输数据等长的堆内存缓冲区Buffer
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        //发送数据 ， 将buffer数据写入channel
        client.write(byteBuffer);
        //让代码停在这
        System.in.read();
    }
}
