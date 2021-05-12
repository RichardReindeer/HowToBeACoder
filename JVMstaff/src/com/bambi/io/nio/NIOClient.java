package com.bambi.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * Nio实现客户都安
 */
public class NIOClient {
    //创建通道管理器
    private Selector selector;

    public void initClient(String ip,int port) throws IOException {
        //获得一个Socket通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置通道为非阻塞
        socketChannel.configureBlocking(false);
        //获得一个通道管理器
        this.selector = Selector.open();

        //客户端连接服务器，其实方法执行并没有实现连接，需要在listen()方法中调用
        //channel.finishConnect()才能完成连接
        socketChannel.connect(new InetSocketAddress(ip,port));
        //将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_CONNECT事件
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
    }

    /**
     * 采用轮询的方式监听selector上是否有需要处理的事件，如果有就处理
     */
    @SuppressWarnings("unchecked")
    public void listen() throws IOException {
        //轮询访问selector
        while (true){
            selector.select();
            //获得selector中选中的项的迭代器
            Iterator iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = (SelectionKey) iterator.next();
                //删除已选的key，以免重复处理
                iterator.remove();
                //如果链接事件发生
                if(key.isConnectable()){
                    SocketChannel channel = (SocketChannel) key.channel();
                    //如果已经处于正在连接的状态了，就确认连接
                    if(channel.isConnectionPending()){
                        channel.finishConnect();
                    }

                    //设置成非阻塞
                    channel.configureBlocking(false);

                    //在这里给服务器端发送信息哦
                    channel.write(ByteBuffer.wrap(new String("这是客户端给服务器端发送的信息呀").getBytes(StandardCharsets.UTF_8)));
                    //与服务器连接成功之后，为了可以接收到服务器端的消息，需要给通道设置读的权限
                    channel.register(selector,SelectionKey.OP_READ);

                    //获得可读的事件
                }else if(key.isReadable()){
                    read(key);
                }
            }
        }
    }


    public void read(SelectionKey key) throws IOException {
        //客户端读取信息，得到事件发生的Socket通道
        //SocketChannel 和 SocketableChannel要强转
        SocketChannel socketChannel = (SocketChannel) key.channel();
        //创建读取的字节缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024*4);
        socketChannel.read(byteBuffer);
        //将字节缓冲池转换为array数组
        byte[] data = byteBuffer.array();
        //使用trim去除前后空白
        String msg = new String(data).trim();
        System.out.println("客户端收到服务端发送的信息了:"+msg);
        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));
        //将信息回送给服务器端
        socketChannel.write(outBuffer);
    }


    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) throws IOException {
        NIOClient nioClient = new NIOClient();
        nioClient.initClient("localhost",8080);
        nioClient.listen();
    }
}



















