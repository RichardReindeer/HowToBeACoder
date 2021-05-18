package com.bambi.io.guigu.nio.zeroCopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 使用Nio实现0拷贝
 */
public class NioServer {
    public static void main(String[] args) throws IOException {

        InetSocketAddress inetSocketAddress = new InetSocketAddress(7001);

        //创建服务器通道
        ServerSocketChannel server = ServerSocketChannel.open();

        server.socket().bind(inetSocketAddress);

        //创建Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //轮询监听
        while (true){
            SocketChannel client = server.accept();

            //设置读取长度
            int readCount = 0;
            while (-1!=readCount){
                try {
                    readCount = client.read(byteBuffer);
                }catch (Exception e){
                    e.printStackTrace();
                }

                //rewind()倒带，将position转至0，mark作废
                byteBuffer.rewind();
            }
        }

    }
}
