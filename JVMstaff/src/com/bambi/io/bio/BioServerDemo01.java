package com.bambi.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 体会Bio编程
 */
public class BioServerDemo01 {
    public static void main(String[] args) throws IOException {
        //申请ServerSocket 服务器端
        System.out.println("开始连接服务器...");
        ServerSocket server = new ServerSocket(6666);
        System.out.println("服务器连接成功");

        //创建线程池用来存放客户端连接
        Executor executor = Executors.newFixedThreadPool(1000);
        while (true){
            System.out.println("与客户端建立连接中...");
            final Socket client = server.accept();
            System.out.println("与客户端建立连接成功");
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    BioServerDemo01.handler(client);
                }
            });

        }
    }

    public static void handler(Socket socket ){
        try {
            System.out.println("为了体现多线程");
            System.out.println("展示一下每个客户端线程的名称:"+Thread.currentThread().getName());
            byte[] data = new byte[1024];
            while (true){
                InputStream inputStream = socket.getInputStream();
                 int readLength  = inputStream.read(data);
                 //如果没有读取到文件末尾
                 if(readLength!=-1){
                     System.out.println(new String(data,0,readLength));
                 }else {
                     break;
                 }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("与客户端连接已断开");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
