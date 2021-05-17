package com.bambi.io.guigu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Nio服务器端
 */
public class NioServer {
    public static void main(String[] args) throws IOException {
        //创建ServerSocketChannel
        System.out.println("创建ServerSocketChannel");
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //得到一个Selector对象
        Selector selector = Selector.open();

        //绑定服务器端口
        serverSocketChannel.socket().bind(new InetSocketAddress("localhost", 8080));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);//这个很重要，毕竟是nio嘛
        //把ServerSocketChannel 注册到selector 关心事件为OP_ACCEPT连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //轮询监听,等待客户端连接
        while (true) {

            //selector.select(long time) 中添加毫秒数，设置没有监听到请求事件时的等待时间
            //selector.selectNow() 则为立即取消等待
            if (selector.select(1000) == 0) {
                System.out.println("等待1s，仍无连接");
                continue;
            }

            //如果返回的值>0则
            //1.如果返回值>0 则表示已经获取到关注的事件
            //2.selector.selectedKeys() 返回关注事件的集合
            //通过selectionKeys反向获取通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //使用迭代器进行遍历
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                //应该分事件处理
                //获取selectionKey
                SelectionKey selectionKey = iterator.next();
                //根据Key对应的通道发生的事件做相应的处理
                if (selectionKey.isAcceptable()) {
                    System.out.println("则有一个新的客户端连接?");
                    //给该客户端生成一个socketChannel
                    SocketChannel client = serverSocketChannel.accept();
                    System.out.println("客户端链接成功，生成了一个socketChannel");
                    //将当前的client注册到selector上
                    //关注读取数据的事件
                    //可以跟这个通道绑定一个缓冲区
                    client.configureBlocking(false);
                    client.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if(selectionKey.isReadable()){
                    //通过key反向获取到对应的channel
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    //获取到channel关联的buffer
                    //selectionKey.attachment()返回的即为client所关联的buffer
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                    client.read(byteBuffer);
                    System.out.println("客户端发送信息，数据为:"+new String(byteBuffer.array()));
                }
                //手动从这个set集合中移除当前的selectionKey
                //防止重复操作
                iterator.remove();
            }

        }

    }
}
