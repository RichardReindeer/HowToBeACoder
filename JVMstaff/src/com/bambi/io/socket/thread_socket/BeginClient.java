package com.bambi.io.socket.thread_socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 模拟客户端
 */
public class BeginClient {
    public static void main(String[] args) throws IOException {
        System.out.println("正在与服务器端连接...");
        Socket client = new Socket("localhost",8888);
        System.out.println("与服务器端成功连接");
        OutputStream outputStream = client.getOutputStream();
        outputStream.write("这是一个想要发送给服务器的信息".getBytes(StandardCharsets.UTF_8));
        //outputStream.flush();
        //不要忘记关闭流
        outputStream.close();
        client.close();
    }
}
