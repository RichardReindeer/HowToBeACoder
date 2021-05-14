package com.bambi.io.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Socket客户端测试
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",8088);
        System.out.println("正在连接服务器");
        System.out.println("服务器连接成功");
        socket.close();
    }
}
