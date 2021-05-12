package com.bambi.io.nioDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

/**
 * Nio是无阻塞io<
 * 它可以用一个线程来接收多个客户端的连接
 * 弊端是需要多次进行内核切换来确认客户端是否存在数据
 */
public class NioDemo01 {
    public static void main(String[] args) throws IOException {
        LinkedList<SocketChannel> clientChannels = new LinkedList<>();
        //获取ServerSocketChannel连接通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //将其设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //将ServerSocket绑定端口号
        serverSocketChannel.socket().bind(new InetSocketAddress(8123));
        while (true){
            //通过accept获取客户端连接
            SocketChannel clientChannel = serverSocketChannel.accept();
            //需要判断获取到的客户端是否为空
            if(clientChannel==null){
                System.out.println("没有获取到客户端连接");
            }else {
                //如果客户端不为空，也需要将其设置为非阻塞socket
                clientChannel.configureBlocking(false);
                //通过getport可以获取端口号
                Integer port = clientChannel.socket().getPort();
                //将客户端socket装入集合中
                clientChannels.add(clientChannel);
            }

            //读取客户端传递过来的数据
            //抛出问题，allocate和allocateDirect的区别?
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            //客户端连接进行遍历
            clientChannels.forEach(socketChannel -> {
                /**
                 * 因为是NIO即非阻塞io，
                 * 所以不管是否接收到客户端的socket都会放入list中，所以需要对client进行读取判断
                 */
                try {
                    //如果读取得到的数值>0则读取到客户端信息
                    //=0没有读取到客户端信息
                    //<0读到中断请求
                    int msg = socketChannel.read(byteBuffer);
                    if(msg>0){
                        //本来read的时候，position指针已经知道信息末尾，调用flip将读模式切换为写模式，即将指针重新放回头部
                        byteBuffer.flip();
                        byte[] data = new byte[byteBuffer.limit()];
                        //进行分支处理
                        byteBuffer.get(data);
                        String message = new String(data);
                        System.out.println(socketChannel.socket().getPort()+"获取到的信息为:"+message);
                        byteBuffer.clear();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }




    }
}
