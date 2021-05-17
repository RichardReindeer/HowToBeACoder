package com.bambi.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 测试SO_LINGER
 */
public class LingerServer01 {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("正在与客户端进行连接");
        Socket client = server.accept();
        System.out.println("与客户端成功连接");
        System.out.println("A socket.getSoLinger()="+client.getSoLinger());
        /**
         * getSoLinger()返回SO_LINGER的设置，如果返回-1意味着禁用选项，
         * 该这是仅仅影响套接字的关闭
         */
        client.setSoLinger(true,0);
        System.out.println("B socket.getSoLinger()="+client.getSoLinger());

        OutputStream outputStream = client.getOutputStream();
        for(int i =0;i<10;i++){
            //将数据发送到客户端中
            outputStream.write("12345678900987654321123456789091039013901293103902391023901".getBytes());
        }
        outputStream.write("end!!!".getBytes());
        System.out.println("socket close before = "+System.currentTimeMillis());
        outputStream.close();
        client.close();
        System.out.println("socket close after = "+System.currentTimeMillis());
        server.close();


    }
}
