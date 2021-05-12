package com.bambi.io.nioByClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * B站视频课代码
 *
 * 一个非常老的bio写法，一个请求进来会连接一个线程
 */
public class SocketBio {
    public static void main(String[] args) throws IOException {
        //申请端口号
        ServerSocket serverSocket = new ServerSocket(9000);

        System.out.println("获取到端口号:9000");
        while (true){
            //服务器端对客户端进行连接，不知道客户端连接与否，所以会出现阻塞
            //只有接收到了客户端的连接，才会返回
            //第一次运行在此发生阻塞，因为没有客户端
            final Socket client = serverSocket.accept();
            System.out.println("与客户端成功建立连接了，客户端的端口号是:"+client.getPort());

            new Thread(()->{
                InputStream inputStream = null;
                try {
                    inputStream = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    while (true){
                        //第二个阻塞
                        String dataLine = reader.readLine();
                        if(null != dataLine){
                            System.out.println(dataLine);
                        }else {
                            //使用完毕之后需要close
                            client.close();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
