package com.bambi.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 测试SO_LINGER的客户端
 */
public class LingerClient01 {

    public static void main(String[] args) throws IOException {
        Socket client = new Socket();
        /**
         * 设置较小的接受缓冲区
         * 目的是先让Server服务发送端close
         * 然后将服务端发送缓冲区中的数据再传入客户端的接受缓冲区中
         * 虽然服务端的socket.close已经执行，但是数据不会丢失
         */
        client.setReceiveBufferSize(1);
        //然后绑定
        client.bind(new InetSocketAddress("loc alhost",7788));
        client.connect(new InetSocketAddress("localhost",8080));

        InputStream inputStream = client.getInputStream();
        byte[] bytes = new byte[1];
        int readLength = inputStream.read(bytes);
        //循环读
        while (readLength!=-1){
            String data = new String(bytes,0,readLength);
            readLength = inputStream.read(bytes);
        }

        System.out.println("client read end time = "+System.currentTimeMillis());
        inputStream.close();
        client.close();

    }
}
