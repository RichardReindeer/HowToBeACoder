package com.bambi.io.guigu.nio.testDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Nio服务器端
 */
public class NioServer03 {

    public static void main(String[] args) {
        //创建服务器端通道
        try {
            //创建多路复用器
            Selector selector = Selector.open();
            //创建服务器socket通道
            System.out.println("创建ServerSocketChannel通道");
            ServerSocketChannel server = ServerSocketChannel.open();
            //将其设置为非阻塞
            server.configureBlocking(false);
            //绑定server的端口
            System.out.println("开始绑定端口号");
            server.socket().bind(new InetSocketAddress(8080));
            System.out.println("绑定端口号成功");
            //将服务器注册到多路复用器中
            server.register(selector, SelectionKey.OP_ACCEPT);
            //采用轮询的方式尝试连接客户端
            while (true){

                //检测是否有客户端连接
                int selectCount = selector.select(1000);
                if(selectCount!=0){
                    System.out.println("selector中存在发生事件的通道");
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    //使用iterator来遍历selectionKeys
                    Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                    while (keyIterator.hasNext()){
                        //获取到存在事件的对应key值
                        SelectionKey key = keyIterator.next();
                        if(key.isAcceptable()){
                            //如果通道事件为acceptable事件
                            /**
                             * 因为server.accept()本身是一个阻塞方法，
                             * 但是先前进行了isAcceptable的判断，所以可以保证每次accept都是有意义的
                             */
                            SocketChannel client = server.accept();
                            //将client设置为非阻塞channel
                            client.configureBlocking(false);

                        }
                    }
                }

            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
