package com.bambi.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * Nio  Non - Blocking IO
 * Nio服务端
 */
public class NIOServer {
    //创建通道管理器
    private Selector selector;

    /**
     * 获得一个ServerSocket通道，并对该通道做一些初始化操作
     * @param port 绑定的端口号啊
     */
    public void initServer(int port) throws IOException {
        //获取一个selectSocket通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //将通道设置为非阻塞通道
        serverSocketChannel.configureBlocking(false);
        //将该通道对应的ServerSocket绑定到port端口
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        //获得一个通道管理器
        this.selector = Selector.open();
        //将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件，注册该事件后
        //当该事件到达时，selector.select()会返回，如果该事件没有到达;selector.select()会一直阻塞
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 采用循环的方式监听selector上是否有需要处理的事件， 如果有就处理
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public void listen() throws IOException {
        System.out.println("服务器端已成功启动");
        //循环访问selector
        while (true){
            //当注册的事件到达时，方法返回，否则 该方法会一直阻塞
            selector.select();
            //获得selector中选中项的迭代器，选中的项为注册的事件
            Iterator iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = (SelectionKey) iterator.next();
                //删除已选择的key，以防止重复处理
                iterator.remove();
                //客户端请求连接事件
                if(key.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    //获得和客户端连接的通道
                    SocketChannel channel = serverSocketChannel.accept();
                    //设置成非阻塞
                    channel.configureBlocking(false);

                    //在这里给客户端发送信息
                    channel.write(ByteBuffer.wrap(new String("向一个客户端发送的信息").getBytes()));
                    //和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置可读权限
                    //SelectionKey.OP_READ为可读权限
                    channel.register(this.selector, SelectionKey.OP_READ);

                    //获得可读的事件
                } else if (key.isReadable()) {
                    read(key);
                }

            }
        }
    }


    public void read(SelectionKey key) throws IOException {
        //服务器可读取信息 得到事件发生的Socket通道
        //SocketChannel 和 SocketableChannel 可以强转?
        SocketChannel socketChannel = (SocketChannel) key.channel();
        //创建读取的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        socketChannel.read(byteBuffer);
        byte[] data = byteBuffer.array();
        //trim方法作用时取除前后空白
        String msg = new String(data).trim();
        System.out.println("服务器收到信息啦:"+msg);
        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));
        //将信息回送给客户端
        socketChannel.write(outBuffer);
    }


    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) throws IOException {
        NIOServer nioServer = new NIOServer();
        nioServer.initServer(8080);
        nioServer.listen();
    }

}

