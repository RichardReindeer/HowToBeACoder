package com.bambi.io.socket;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) throws IOException {


        //创建ServerSocket服务端
        //设置端口号
        ServerSocket serverSocket = new ServerSocket(8088);
        //等待客户端连接，如果不连接则会线程阻塞
        System.out.println("正在等待连接，如果没有客户端连接则一直阻塞");
        serverSocket.accept();
        System.out.println("连接成功");
        //长连接特点:不会自己关闭，避免发生多次重连接(多次握手)
        serverSocket.close();
    }
}
