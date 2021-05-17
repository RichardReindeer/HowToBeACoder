package com.bambi.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpNagleServer02 {
    public static void main(String[] args) throws IOException {
        //创建服务器端口
        System.out.println("正在申请端口号");
        ServerSocket socket = new ServerSocket(8080);
        System.out.println("端口号申请成功过");
        System.out.println("正在获取已连接的客户端");
        Socket client =socket.accept();
        System.out.println("客户端获取成功");
        System.out.println("A = "+client.getTcpNoDelay());//默认启用Nagle算法，所以为false
        //client.setTcpNoDelay(true);//禁用Nagle算法，立即发送，不缓存数据，不启用nagle算法
        //System.out.println("B = "+ client.getTcpNoDelay());

        //向服务器发送数据
        OutputStream outputStream = client.getOutputStream();
        for(int i =0 ; i<50000 ; i++){
            outputStream.write("1".getBytes());
        }
        socket.close();
        client.close();
    }
}
