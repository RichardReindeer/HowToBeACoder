package com.bambi.io.guigu.nio.zeroCopy;


import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//传统javaIO服务器
public class OldServer {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(7001);

        while (true){
            Socket client = server.accept();
            DataInputStream inputStream = new DataInputStream(client.getInputStream());

            try {
                byte[] byteArrays = new byte[4096];

                while (true){
                    int readCount = inputStream.read(byteArrays,0,byteArrays.length);

                    if(-1==readCount){
                        break;
                    }
                }
            }catch (Exception e ){

            }
        }
    }
}
