package com.bambi.io.guigu.nio.testDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 测试Nio客户端
 */
public class NioClient01 {
    public static void main(String[] args) throws IOException {
        //创建客户端socket通道
        SocketChannel client = SocketChannel.open();
        //将其设置为非阻塞channel
        client.configureBlocking(false);
        System.out.println("正在连接客户端");
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost",8080);
        if(!client.connect(inetSocketAddress)){
            //如果使用connect连接失败，则使用finishConnect的方式连接
            while (!client.finishConnect()){
                System.out.println("尝试使用finishConnect方式连接");
            }
        }

        //如果连接上了，开始给服务器端发送数据
        String message = "这是要发送给服务器端的数据";
        ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes());
        //将数据从channel中写入到byteBuffer中
        client.write(byteBuffer);

        //悬停
        System.in.read();
    }
}
