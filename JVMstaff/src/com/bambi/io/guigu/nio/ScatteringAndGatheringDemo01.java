package com.bambi.io.guigu.nio;

import com.bambi.thread.volatileLearn.Arr;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Scattering   将数据写入到Buffer时，可以采用Buffer数组依次写入 (分散)
 * Gathering    从Buffer读出数据时，可以采用Buffer数组一次读写
 */
public class ScatteringAndGatheringDemo01 {
    public static void main(String[] args) throws IOException {

        //ServerSocketChannel 和SocketChannel
        //创建一个ServerSocketCHannel

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8080);

        //绑定端口到Socket  并启动
        serverSocketChannel.socket().bind(inetSocketAddress);

        //创建一个Buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等待客户端连接
        System.out.println("等待客户端连接");
        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println("客户端已连接");
        int messageLength = 8;//假定从客户端接收8个字节

        while (true) {

            int byteRead = 0;

            while (byteRead < messageLength) {
                long l = socketChannel.read(byteBuffers);
                //累计读取到的字节数
                byteRead += l;
                System.out.println("累计读取到的字节数:byteRead = " + byteRead);

                //使用流打印，观察当前的这个buffer的position 和limit
                Arrays.asList(byteBuffers).stream().map(byteBuffer -> "position" + byteBuffer.position()
                        + ",limit " + byteBuffer.limit()
                ).forEach(System.out::println);
            }

            //拿到全部数据后需要输出
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());
            //将数据读出，显示到客户端中

            //定义变量设置回显大小
            long byteWrite = 0;

            //当读取到的字节小于设置的回显字节大小限制时，持续进行写操作
            while (byteWrite<messageLength){
                long write  = socketChannel.write(byteBuffers);
                byteWrite++;
            }

            //将所有的bytebuffer 进行复位
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());

            //将得到结果输出
            System.out.println("byteRead = "+byteRead +" byteWrite = "+byteWrite);

        }

    }
}
