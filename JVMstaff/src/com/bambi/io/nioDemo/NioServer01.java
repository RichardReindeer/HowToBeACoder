package com.bambi.io.nioDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * 模拟Nio服务器端
 */
public class NioServer01 {

    private Selector selector;

    /**
     * 给服务器端设置端口号
     * @param port
     */
    public void initServer(int port) throws IOException {
        //获取一个ServerSocketChannel通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //将其设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //设置端口号
        serverSocketChannel.socket().bind(new InetSocketAddress(9000));

        this.selector = Selector.open();
        //将通道管理器和通道绑定，并将该通道注册为OP_ACCEPT事件，达能该事件发生，selector.select()会返回
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 循环监听selector上是否有需要处理的事件
     */
    public void listen() throws IOException, InterruptedException {
        System.out.println("服务器已成功启动");
        while (true){
            //当注册事件到达时，方法返回，否则会一直阻塞
            selector.select();
            //获取selector中的迭代器，来遍历注册的事件
            Iterator iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = (SelectionKey) iterator.next();
                //删除已经选择的key,防止重复处理
                iterator.remove();
                if(key.isAcceptable()){
                    System.out.println("客户端已成功连接");
                    ServerSocketChannel serverSocket = (ServerSocketChannel) key.channel();
                    SocketChannel client = serverSocket.accept();
                    //获取客户端socket连接通道
                    client.configureBlocking(false);
                    //给客户端发送信息
                    client.write(ByteBuffer.wrap(new String("发送信息给客户端，"+serverSocket.socket().getLocalPort()).getBytes()));
                    //和客户端连接成功之后，将客户端的权限设置为可读，来读取客户端发送过来的信息
                    client.register(selector,SelectionKey.OP_READ);
                }else if(key.isReadable()){
                    //进行读取信息的操作
                    read(key);
                }
            }
        }
    }

    /**
     * 读取信息的操作
     * @param key
     */
    private void read(SelectionKey key) throws InterruptedException, IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        //创建用来读取的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        socketChannel.read(byteBuffer);
        byte[] data = byteBuffer.array();
        String message = new String(data).trim();
        System.out.println("服务器端获取到信息了,"+message);
        Thread.sleep(1000);
        ByteBuffer outByteBuffer = ByteBuffer.wrap(message.getBytes());
        //将信息返回给客户端
        socketChannel.write(outByteBuffer);
    }

    public static void main(String[] args) throws Exception {
        NioServer01 nioServer01 = new NioServer01();
         nioServer01.initServer(9000);
         nioServer01.listen();
    }
}
