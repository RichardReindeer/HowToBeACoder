package com.bambi.io.nioDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * 模拟Nio客户端
 */
public class NioClient01 {
    private Selector selector;

    public void initClient() throws IOException {
        //获取客户端socket通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置为非阻塞
        socketChannel.configureBlocking(false);
        //将多路复用器与其绑定
        this.selector = Selector.open();
        //获取需要连接的端口的端口号
        socketChannel.connect(new InetSocketAddress("localhost",9000));

        socketChannel.register(selector, SelectionKey.OP_CONNECT);
    }

    /**
     * 采用循环询问的方式监听selector上是否有需要处理的事件，如果有就处理
     */
    public void listen() throws IOException, InterruptedException {
        while (true){
            selector.select();
            Iterator iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = (SelectionKey) iterator.next();
                //删除已选的key，防止重复处理
                iterator.remove();
                //如果发生了连接事件
                if(key.isConnectable()){
                    SocketChannel channel = (SocketChannel) key.channel();
                    //channel.finishConnect();
                    //判断是否正在连接，如果正在连接则确认连接
                    if(channel.isConnectionPending()){
                        channel.finishConnect();
                    }

                    channel.configureBlocking(false);

                    //给服务器发送信息
                    channel.write(ByteBuffer.wrap(new String("这是给服务器发送的信息").getBytes()));
                    //与服务器连接成功之后，接收服务器发送的信息，所以需要给channel设置读信息的权限
                     channel.register(selector,SelectionKey.OP_READ);
                }else if(key.isReadable()){
                    //进行读操作
                    read(key);
                }
            }
        }
    }

    //进行读取信息操作
    private void read(SelectionKey key ) throws InterruptedException, IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        socketChannel.read(byteBuffer);
        byte[] data = new byte[byteBuffer.limit()];
        String message = new String(data).trim();
        System.out.println("开始接收服务器端发送的信息");
        Thread.sleep(1000);
        ByteBuffer outByteBuffer= ByteBuffer.wrap(new String("获取到了服务器端发送的信息并开始回复").getBytes());
        socketChannel.write(outByteBuffer);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        NioClient01 nioClient01 = new NioClient01();
        nioClient01.initClient();
        nioClient01.listen();
    }
}
