package com.bambi.io.guigu.nio;

import com.bambi.thread.volatileLearn.Arr;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Nio
 * 可以将数据写入到一个Buffer数组中 Scattering
 * 也可以将buffer数组一起读出来
 */
public class ScatteringAndGatheringTest01 {

    public static void main(String[] args) throws IOException {

        //创建一个服务器端口
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定端口号
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));

        //创建一个Buffer数组来存储数据
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        //给数组中buffer分配空间
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(4);

        //接收客户端连接
        System.out.println("无客户端连接，正在阻塞");
        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println("客户端连接成功");

        //设置最大一次可读的字节数
        long messageLimit = 9;
        while (true) {
            long byteRead = 0;
            //当读取到的字节数小于限制时继续读取
            while (byteRead < messageLimit) {
                //读取
                long read = socketChannel.read(byteBuffers);
                byteRead += read;
                System.out.println("累计读取到的字节数为:"+byteRead);
            }

            //读取完毕之后需要对这些流进行反转
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());
            //将读取到的字节输出 Gathering
            long byteWrite = 0;

            while (byteWrite<messageLimit){
                //将客户端发送的信息写入缓冲区
                long write = socketChannel.write(byteBuffers);
                byteWrite++;
            }
            String message = new String(Arrays.asList(byteBuffers).toString());
            System.out.println("读取到的客户端发送来的信息:"+message);
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());
        }

    }
}
