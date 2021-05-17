package com.bambi.io.guigu.nio.testDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * 练习建立Nio服务器端
 */
public class NioServer01 {
    public static void main(String[] args) throws IOException {
        //创建多路复用器
        Selector selector = Selector.open();

        //创建服务器端通道
        //服务器端和客户端进行握手的并不是从accept()开始的，在服务器端创建并绑定到指定端口时，就已经开始
        ServerSocketChannel server = ServerSocketChannel.open();
        //将其设置为非堵塞
        server.configureBlocking(false);
        //绑定端口号
        server.socket().bind(new InetSocketAddress("localhost",8080));

        //在多路复用器中注册服务器
        server.register(selector, SelectionKey.OP_ACCEPT);

        //采用轮询的方式连接客户端
        while (true){
            //因为设置了服务器通道为非阻塞，所以不管是否接收到客户端，程序都会继续执行
            //使用多路复用器selector 的select方法来获取客户端的连接
            if (selector.select(5000)==0){
                System.out.println("没有客户端连接");
                continue;
            }

            //获取装有在selector中注册了的通道的set集合
            Set<SelectionKey> keys = selector.selectedKeys();
            //使用iterators来进行遍历
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()){
                //获取key 对象
                SelectionKey key = iterator.next();
                //根据key的不同事件进行判断
                if(key.isAcceptable()){
                    //根据key获取客户端
                    System.out.println("等待客户端连接");
                    SocketChannel client = server.accept();
                    System.out.println("客户端成功连接");
                    //将客户端设置为非阻塞
                    client.configureBlocking(false);
                    //将其注册到多路复用器中
                    client.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                //如果key的事件是OP_READ，则读取客户端发来的信息
                if(key.isReadable()){
                    //获取客户端通道
                    SocketChannel client = (SocketChannel) key.channel();
                    //client.configureBlocking(false);
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    client.read(byteBuffer);
                    System.out.println("客户端发来的信息为:");
                    String message = new String(byteBuffer.array());
                    System.out.println(message.trim());
                }

                //考虑线程安全问题，需要将使用过的key从set集合中清除
                iterator.remove();
            }
        }
    }
}
