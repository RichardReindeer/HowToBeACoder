package com.bambi.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 使用线程池机制创建BIO服务器端
 * <p>
 * 思路:
 * 1.创建一个线程池
 * 2.如果有客户端连接，就创建一个线程与之通讯
 */
public class BioServer {
    public static void main(String[] args) throws IOException {

        //创建一个线程池
        System.out.println("创建一个服务器端");
        Executor pool = Executors.newFixedThreadPool(1000);

        //创建一个服务器端
        ServerSocket server = new ServerSocket(8080);
        //每当有一个客户端连接，就开启一个线程
        while (true) {
            //因为不知道有多少个线程，所以是死循环
            System.out.println("开始与客户端建立连接");
            final Socket socket = server.accept();
            System.out.println("与客户端成功连接");
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        BioServer.handler(socket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 编写一个handler方法，和客户端进行通讯
     * @param socket
     * @throws IOException
     */
    public static void handler(Socket socket) throws IOException {

        System.out.println("获取当前线程的名字:"+Thread.currentThread().getName());
        //创建一个字节数组去指定一次读取的长度
        byte[] data = new byte[1024];
        //获取客户端发送的输入流信息
        InputStream inputStream = socket.getInputStream();
        while (true) {
            //读取数据如果没有读取到也会阻塞
            int readLength = inputStream.read(data);
            if (readLength != -1) {
                System.out.println("客户端发送的信息是:" + new String(data,0,readLength));

            } else {
                break;
            }
        }
        socket.close();
        inputStream.close();
    }
}
