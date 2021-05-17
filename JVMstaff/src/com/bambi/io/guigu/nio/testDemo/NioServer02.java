package com.bambi.io.guigu.nio.testDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Nio服务器端
 */
public class NioServer02 {
    public static void main(String[] args) throws IOException {
        //声明一个多路复用器
        Selector selector = Selector.open();
        //创建ServerSocketChannel
        ServerSocketChannel server = ServerSocketChannel.open();
        //将server设置为非阻塞io
        server.configureBlocking(false);
        //将server绑定指定端口
        //TCP/IP中的三次握手从绑定端口开始
        server.socket().bind(new InetSocketAddress(8080));
        //将server注册到多路复用器中
        server.register(selector, SelectionKey.OP_ACCEPT);

        //采用轮询的方式获取客户端
        while (true){

            if(selector.select(50000)==0){
                System.out.println("如果等待50秒仍然没有客户端来连接");
                continue;
            }

            //创建存放注册到selector的channel的set集合，底层是HashSet , HashSet特点: 无序
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //采用Iterator遍历
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if(key.isAcceptable()){
                    //如果事件为连接事件，则获取client客户端并绑定端口
                    /**
                     * 因为accept()方法本身为一个阻塞方法，但是前面使用isAcceptable进行筛选，确保进行的accept()都有意义
                     */
                    SocketChannel client = server.accept();
                    client.configureBlocking(false);
                    client.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if(key.isReadable()){
                    //如果是读事件，则进行客户端数据的读取
                    SocketChannel client = (SocketChannel) key.channel();
                    //创建一个动态长度的byteBuffer , 长度跟attachment大小一致
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    int readLength = client.read(byteBuffer);
                    System.out.println("客户端发送的信息是:");
                    System.out.println(new String(byteBuffer.array()).trim());
                    if(readLength==-1){
                        byteBuffer.clear();
                    }
                }

                //为了防止发生线程安全问题，在使用完毕之后需要将该key值remove掉
                iterator.remove();
            }
        }
    }
}
