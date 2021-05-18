package com.bambi.io.guigu.nio.zeroCopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * 使用Nio的0拷贝实现文件传输
 */
public class NioClient {

    public static void main(String[] args) throws IOException {
        SocketChannel client = SocketChannel.open(new InetSocketAddress("localhost",7001));
        client.configureBlocking(false);

        String fileName = "28.png";
        //得到文件channel
        FileChannel channel = new FileInputStream(fileName).getChannel();

        //准备发送
        long startTime  = System.currentTimeMillis();
        //在Linux下，一个transferTo 方法就可以完成传输
        //在windows下，一次最多只能发送8M文件就需要分段传输文件
        //要注意传输时的位置
        //position 拷贝的起始位置，大小， 拷贝到客户端通道中
        long transferCount = channel.transferTo(0,channel.size(),client);
        System.out.println("发送的总字节数:"+transferCount);
        System.out.println("总共花费的时间:"+(System.currentTimeMillis()-startTime));

    }
}
