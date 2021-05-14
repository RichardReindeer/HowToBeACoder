package com.bambi.io.socket.thread_socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 采用多线程实现通信
 * 通过多线程实现客户端信息的读取操作
 */
public class BeginThread extends Thread {
    private Socket socket ;

    public BeginThread(Socket socket) {
        this.socket = socket;
    }

    /**
     * 使用多线程来进行与客户端的交互
     */
    @Override
    public void run() {
        try {
            //接收客户端发送的信息
            InputStream inputStream = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            //BufferedReader bufferedReader = new BufferedReader(reader);
            char[] charArray = new char[1000];
            int readLength = -1;
            while ((readLength=reader.read(charArray))!=-1){
                String message = new String(charArray,0,readLength);
                System.out.println("读取到的信息是;"+message);
                break;
            }
            reader.close();
            inputStream.close();
            //客户端既然在这里调用就需要在这里关闭
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
