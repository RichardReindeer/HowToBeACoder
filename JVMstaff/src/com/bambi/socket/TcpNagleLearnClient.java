package com.bambi.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 客户端代码
 */
public class TcpNagleLearnClient {
    public static void main(String[] args) throws IOException {
        System.out.println("正在连接服务器端");
        Socket client = new Socket("localhost",8080);
        System.out.println("服务器连接成功");
        InputStream inputStream = client.getInputStream();

        long start = System.currentTimeMillis();
        byte[] byteArray =new byte[1];
        int readLength = inputStream.read(byteArray);
        while (readLength!=-1){
            String newString = new String(byteArray,0,readLength);
            System.out.println(newString);
            readLength = inputStream.read(byteArray);
        }

        long end = System.currentTimeMillis()-start;
        System.out.println("总耗时:"+end);
        client.close();
    }
}
