package com.bambi.io.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 创建一个简易的web服务器
 */
public class CreateWebServer {

    public static void main(String[] args) throws IOException {
        //创建服务器并申请端口号
        ServerSocket socket = new ServerSocket(8080);
        //accept方法是有返回值的，返回值为新加入的客户端连接
        Socket client = socket.accept();
        InputStream inputStream = client.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String message = "";
        //判断客户端是否有向服务器端看发送信息，如果发送成功则输出
        while (!"".equals(message = bufferedReader.readLine())){
            System.out.println("读取到的客户端发送信息为:"+message);
        }
        OutputStream outputStream = client.getOutputStream();
        outputStream.write("HTTP/1.1 200 OK\r\n\r\n".getBytes(StandardCharsets.UTF_8));
        //考虑显示中文的问题
        outputStream.write(
                "<html><head><meta charset='UTF-8'></head><body><a href='http://www.baidu.com'> 点击这里访问百度 </a></body></html>"
                        .getBytes()
        );
        outputStream.flush();
        inputStream.close();
        outputStream.close();
        client.close();
        socket.close();
    }
}
