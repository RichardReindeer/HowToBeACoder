package com.bambi.io.socket.tcp;

import java.io.IOException;
import java.net.Socket;

/**
 * TCP/IP四次分手 客户端测试
 *
 */
public class BreakHandsClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Long start = System.currentTimeMillis();
        Socket client = new Socket("localhost",8080);
        Long end = System.currentTimeMillis()-start;
        System.out.println("已经成功建立连接");
        System.out.println("总共耗时:"+end);
        client.close();
        Thread.sleep(2000);
    }

}
