package com.bambi.io.guigu.nio.testDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Nio群聊系统服务器端
 */
public class NioGroupChatServerDemo01 {

    //设定端口号常量
    private final int PORT = 8080;
    //声明多路复用器对象
    private Selector selector;
    //声明服务端通道
    private ServerSocketChannel server;

    public NioGroupChatServerDemo01() throws IOException {
        //在构造方法中实例化
        server = ServerSocketChannel.open();
        //设置为非阻塞
        server.configureBlocking(false);
        //绑定端口
        server.socket().bind(new InetSocketAddress(PORT));
        //注册
        selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);
    }

    //采用轮询的方式监听客户端
    public void listen() throws IOException {
        while (true) {
            int count = selector.select(1000);
            if (count == 0) {
                System.out.println("等待连接中");
                continue;
            }
            //如果count != 0 则表明有注册到selector中的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            //对迭代器进行遍历
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //如果通道为accept事件
                if (key.isAcceptable()) {
                    //则完成客户端的连接
                    SocketChannel client = server.accept();
                    //将其设置为非阻塞
                    client.configureBlocking(false);
                    //注册到多路复用器上并输出是上线信息
                    client.register(selector, SelectionKey.OP_READ);
                    System.out.println(client.getRemoteAddress() + " is Online");
                }
                if (key.isReadable()) {
                    //进行客户端信息的读取
                    readData(key);
                }
                //记得将调用到的set集合中的key remove掉，避免被重复调用
                iterator.remove();
            }
        }
    }

    /**
     * 读取数据的方法
     * @param key
     */
    private void readData(SelectionKey key) {
        //声明一个客户端通道
        SocketChannel client = null;
        try {
            client = (SocketChannel) key.channel();
            //创建一个与客户端传输内容等长的缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            //让客户端读一下
            int readLength = client.read(byteBuffer);
            //如果读到的内容>0就打印输出
            if (readLength != -1) {
                String message = new String(byteBuffer.array()).trim();
                System.out.println(client.getRemoteAddress() + " 说 :" + message);

                //需要将读取到的信息发送给其他客户端，群聊嘛
                sendMessageToOtherClient(message,key);
            }
        } catch (IOException e) {
            //如果报出异常，则通知已经下线
            System.out.println("客户端下线");
            //将这个key 拿掉
            key.cancel();
            try {
                client.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * 将信息发送给其他客户端
     * @param message
     * @param key
     */
    private void sendMessageToOtherClient(String message, SelectionKey key) throws IOException {
        Channel client = key.channel();
        //创建装有发送信息的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes());
        //将缓冲区中的分析写入到客户端中
        SocketChannel clientChannel = (SocketChannel) client;
        clientChannel.write(byteBuffer);
    }

    public static void main(String[] args) throws IOException {
        NioGroupChatServerDemo01 nioGroupChatServerDemo01 = new NioGroupChatServerDemo01();
        nioGroupChatServerDemo01.listen();
    }
}

