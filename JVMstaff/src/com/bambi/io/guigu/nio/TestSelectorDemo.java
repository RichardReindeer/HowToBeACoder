package com.bambi.io.guigu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TestSelectorDemo {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        //获取服务端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定端口号
        System.out.println("开始绑定端口号");
        serverSocketChannel.socket().bind(new InetSocketAddress("localhost",7777));
        System.out.println("绑定端口号完毕");
        //将服务器端注册到selector中
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //获取客户端
        SocketChannel client = serverSocketChannel.accept();
        client.configureBlocking(false);
        client.register(selector,SelectionKey.OP_CONNECT);
        //需要通过此方法完成连接
        client.finishConnect();
    }
}
