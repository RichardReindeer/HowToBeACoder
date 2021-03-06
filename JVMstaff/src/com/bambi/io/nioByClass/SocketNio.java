package com.bambi.io.nioByClass;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

/**
 * nio  站在java的角度  n是new的意思
 * 站在内核的角度 n是non block 的意思
 *
 *
 * ServerSocketChannel
 * Java Nio 中ServerSocketChannel 是一个可以监听新进来的TCP连接的通道，
 * 就像bio中的ServerSocket一样
 *
 *
 * ServerSocketChannel方法调用:
 *  ServerSocketChannel open()   获取一个ServerSocket通道 返回一个ServerSocketChannel
 *
 *  void configureBlocking()  设置通道是否阻塞，false为非阻塞
 *
 *   socket().bind(InetSocketAddress(int port))  将通道对应的ServerSocket绑定到对应端口上
 *
 *   ServerSocketChannel accept() 通过accept来监听新进来的连接 返回值是新加进来的连接的SocketChannel
 *   因此accept会一直阻塞，直到连接到新的ServerSocket 可以通过循环调用accept的方法监听多个连接
 *
 *   在非阻塞模式下，accept会立刻返回，如果没有新连接为null,
 *
 */
public class SocketNio {
    public static void main(String[] args) throws Exception{
        LinkedList<SocketChannel> clients = new LinkedList<>();

        //服务器开始监听，接收客户端
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.bind(new InetSocketAddress(9000));
        //阻塞之后只接收客户都安，不阻塞
        serverSocketChannel.configureBlocking(false);//<--调用了内核的nonBlock 设置为非阻塞

        while (true){
            //接收客户端的连接
            /**
             * 与客户端连接
             * 既然是不阻塞，如果没有客户端连接怎么办呢?
             * 在内核中，如果没有客户端连接，就返回-1，有则返回5，6这样的数值
             *
             */
            SocketChannel client = serverSocketChannel.accept();

            if(client==null){
                System.out.println("没有客户端连接");
            }else {
                //对client也要设置非阻塞，让其对后面的读写不进行阻塞
                client.configureBlocking(false);
                int port = client.socket().getPort();
                System.out.println("---------------------------------");
                System.out.println("client...port;"+port);
                System.out.println("----------------------------------");
                clients.add(client);
            }


            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4096);

            //遍历已经链接进来的客户端能否读写数据
            clients.forEach(socketChannel -> {
                try {
                    /**
                     * 因为上面设置了非阻塞，
                     * 所以就算没有客户端进行链接，还是会进行下面的语句，
                     * read读到的数据大概如下
                     * 0  没读到
                     * -1 读取到断开连接语句
                     * >0 读到数据
                     */
                    int num = socketChannel.read(byteBuffer);
                    //则进行分支处理
                    if(num>0){
                        byteBuffer.flip();
                        byte[] data = new byte[byteBuffer.limit()];
                        byteBuffer.get(data);

                        String msg = new String(data);
                        System.out.println(socketChannel.socket().getPort()+":"+msg);
                        byteBuffer.clear();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        }


    }
}
