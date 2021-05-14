package com.bambi.io.socket.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 四次挥手(分手)测试
 */
public class BreakHandsServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("开始阻塞，等待客户端连接");
        Socket client = server.accept();
        System.out.println("阻塞结束，已经接收到客户端连接");
        client.close();
        server.close();
        Thread.sleep(2000);
    }

}
