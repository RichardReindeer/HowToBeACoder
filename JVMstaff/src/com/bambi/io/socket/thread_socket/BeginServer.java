package com.bambi.io.socket.thread_socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 结合多线程Thread实现通信
 */
public class BeginServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        int runTag = 1;
        while (runTag==1){
            //获取客户端
            Socket client = server.accept();
            BeginThread beginThread = new BeginThread(client);
            beginThread.start();
        }
        server.close();
    }
}
