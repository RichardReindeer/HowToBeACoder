package com.bambi.io.guigu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.List;

/**
 * 群聊聊天室服务器端
 */
public class GroupChetServer {

    //定义属性
    private Selector selector;
    private ServerSocketChannel listenChannel;
    //指定监听端口
    private static final int PORT = 6667;


    //构造器中完成初始化
    public GroupChetServer() {
        //得到选择器:
        try {
            selector = Selector.open();
            //ServerSocketChannel
            listenChannel = ServerSocketChannel.open();
            //绑定端口
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            //设置为非阻塞
            listenChannel.configureBlocking(false);
            //将listionChannel注册到selector
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //监听方法
    public void listen() {
        //轮询监听客户端
        while (true) {

            try {
                int count = selector.select(2000);

                //有注册到selector中的客户端
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        //取出selectionKey
                        SelectionKey key = iterator.next();

                        //监听到accept
                        if (key.isAcceptable()) {
                            //使用accept()拿到客户端
                            SocketChannel client = listenChannel.accept();
                            //将该client注册到selector中
                            client.register(selector, SelectionKey.OP_READ);

                            //给出提示
                            System.out.println(client.getRemoteAddress() + "上线了");
                        }

                        //发生可读事件
                        if (key.isReadable()) {
                            //处理读工作

                        }

                        //将当前key删除，避免重复处理
                        iterator.remove();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {

    }
}
