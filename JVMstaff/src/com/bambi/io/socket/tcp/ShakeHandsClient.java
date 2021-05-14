package com.bambi.io.socket.tcp;

import java.io.IOException;
import java.net.Socket;

public class ShakeHandsClient {
    public static void main(String[] args) {

        try {
            //创建客户端准备连接
            Long start = System.currentTimeMillis();
            Socket socket = new Socket("localhost", 6666);
            Long end = System.currentTimeMillis()-start;
            System.out.println("客户端创建完毕");
            System.out.println("创建连接消耗的毫秒数为:"+end);
            System.out.println("避免过早关闭，方便观察");
            Thread.sleep(500000000);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
