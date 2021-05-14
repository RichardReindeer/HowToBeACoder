package com.bambi.io.socket.tcp;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * 测试TCP的三次握手协议
 */
public class ShakeHandsServer {
    public static void main(String[] args) {
        try {
            //创建一个服务端
            ServerSocket socket = new ServerSocket(6666);
            System.out.println("查看一下会阻塞多长事件");
            Long start  = System.currentTimeMillis();
            socket.accept();
            Long end = System.currentTimeMillis()-start;
            System.out.println("一共阻塞等待了:"+end);
            //避免过早关闭，方便观察
            Thread.sleep(Integer.MAX_VALUE);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
