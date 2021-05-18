package com.bambi.io.guigu.nio.testDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * NIO群聊客户端
 */
public class NioGroupChatClientDemo01 {
    private Selector selector;
    private final int PORT = 8080;
    private final String HOST = "127.0.0.1";
    private SocketChannel client ;
    private String username = "";

    /**
     * 在构造方法中完成通道的创建，注册，以及连接服务器操作
     */
    public NioGroupChatClientDemo01() {
        try {
            System.out.println("创建客户端通道");
            client = SocketChannel.open();
            System.out.println("通道创建完毕");
            client.configureBlocking(false);
            client.socket().bind(new InetSocketAddress(HOST, PORT));
            //实例化多路复用器
            selector = Selector.open();
            client.register(selector, SelectionKey.OP_READ);
            //输出已登录信息
            username = client.getLocalAddress().toString();
            System.out.println(username + " 已经上线了 ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向服务器端发送信息
     */
    public void sendInfo(String info){
        info = "说:  "+info;
        try {
            client.write(ByteBuffer.wrap(info.getBytes(StandardCharsets.UTF_8)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 客户端读取信息的操作
     */
    public void readInfo(){

        //读取用户信息
    }
}
