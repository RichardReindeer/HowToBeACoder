package com.bambi.io.guigu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
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

                            client.configureBlocking(false);
                            //将该client注册到selector中
                            client.register(selector, SelectionKey.OP_READ);

                            //给出提示
                            System.out.println(client.getRemoteAddress() + "上线了");
                        }

                        //发生可读事件
                        if (key.isReadable()) {
                            //处理读工作
                            readData(key);
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

    //读取客户端消息
    private void readData(SelectionKey key) {

        //定义一个socketChannel
        SocketChannel client = null;
        try{
            //获取到关联的channel
            client = (SocketChannel) key.channel();
            //创建缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            //返回的是读取到的长度
            int count = client.read(byteBuffer);
            //根据count的值做处理
            if(count>0){
                //把缓冲区的数据转成字符串并输出
                String message = new String(byteBuffer.array());
                //输出该消息
                System.out.println("from 客户端:"+message);

                //向其他客户端转发消息,专门写一个方法来处理
                sendInfoToOtherClients(message,client);

            }

        } catch (IOException e) {
            try {
                //getRemoteAddress (获取到client的远程地址)
                System.out.println(client.getRemoteAddress()+"已经下线");
                //取消注册
                key.cancel();
                //关闭通道
                client.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    //转发消息给其他客户

    /**
     *
     * @param message 客户端发送过来的消息
     * @param self 发送信息的客户端自己，需要排除
     */
    private void sendInfoToOtherClients(String message , SocketChannel self) throws IOException {
        System.out.println("服务器转发消息");
        //遍历所有注册到selector 上的socketCHannel 并排除self
        for (SelectionKey key : selector.keys()){

            //通过Key获取对应通道  使用类实现的接口来接收
            Channel targetChannel =  key.channel();

            //排除自己
            //既保证targetChannel 是一个客户端，也要保证不是发送消息的channel本身
            if(targetChannel instanceof SocketChannel && targetChannel!=self){

                //转型
                SocketChannel target = (SocketChannel) targetChannel;
                //将message存储到buffer
                ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes());
                //将byeBuffer中的数据写入到通道中
                target.write(byteBuffer);
            }
        }
    }


    public static void main(String[] args) {
        GroupChetServer server = new GroupChetServer();
        server.listen();
    }


}
