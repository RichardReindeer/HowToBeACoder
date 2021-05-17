package com.bambi.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket中调用TcpNoDelay来选择是否使用Nagle算法
 *
 * Nagle算法:
 *      用于解决Tcp/IP拥塞的控制方法
 *      比如如果想向服务器端传输1字节数据，那么在网络中将会产生41字节的数据包(因为TCP会自行添加协议头)
 *      小数据包众多会造成严重的服务器端压力
 * Nagle算法实现原理:
 *      是在客户端未确认ACK之前让发送器把数据送到缓存中
 *      后面的数据也相继送到缓存中，直到得到确认ACK之后在攒到一定大小(size)发送
 *
 * Nagel特点:
 *      提高网络吞吐量，但是实时性降低
 *      适用于不要求高实时性，减少网络交互的场景
 * *******
 * 注意，在Socket创建时，都是默认使用Nagle算法的，这回导致交互速度严重下降，
 * 需要手动关闭
 * *******
 */
public class TcpNagleLearnServer {
    public static void main(String[] args) throws IOException {
        //创建服务器端口
        ServerSocket socket = new ServerSocket(8080);
        Socket client =socket.accept();
        System.out.println("A = "+client.getTcpNoDelay());//默认启用Nagle算法，所以为false
        client.setTcpNoDelay(true);//禁用Nagle算法，立即发送，不缓存数据，不启用nagle算法
        System.out.println("B = "+ client.getTcpNoDelay());

        //向服务器发送数据
        OutputStream outputStream = client.getOutputStream();
        for(int i =0 ; i<50000 ; i++){
            outputStream.write("1".getBytes());
        }
        socket.close();
        client.close();
    }
}
