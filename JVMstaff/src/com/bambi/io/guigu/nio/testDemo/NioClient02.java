package com.bambi.io.guigu.nio.testDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Nio的client客户端
 */
public class NioClient02 {
    public static void main(String[] args) throws IOException {
        //创建客户端
        System.out.println("创建客户端socket通道");
        SocketChannel client = SocketChannel.open();
        System.out.println("通道创建完毕");
        //设置为非阻塞
        client.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8080);

        //进行连接判断
        if(!client.connect(inetSocketAddress)){
            //如果使用connect方法连接失败，则尝试使用finishConnect方法进行连接
            while (!client.finishConnect()){
                System.out.println("正在尝试使用finishConnect方法连接");
            }
        }

        //如果连接成功，则开始向服务器端发送数据
        String message = "这是向服务器端发送的数据";
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
        //将byteBuffer写入channel中
        client.write(buffer);
        System.out.println("信息发送完毕?");
        System.in.read();

    }
}
