package com.bambi.io.guigu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1.连接服务器
 * 2.发送消息
 * 3.接收服务器消息
 *
 */
public class GroupChetClient {

    //定义相关属性
    private final String HOST = "127.0.0.1";//服务器的IP
    private final int PORT = 6667;
    private Selector selector;
    //客户端通道
    private SocketChannel client ;
    private String username ;

    //构造器 完成初始化工作
    public GroupChetClient() throws IOException {
        //初始化多路复用器
        selector = Selector.open();
        //连接服务器
        client = SocketChannel.open(new InetSocketAddress(HOST,PORT));
        //设置非阻塞
        client.configureBlocking(false);
        //将channel 注册到selector
        client.register(selector, SelectionKey.OP_READ);
        //得到username  获取本低地址
        username = client.getLocalAddress().toString().substring(1);
        System.out.println(username+"is OK");
    }

    //向服务器发送消息
    public void sendInfo(String info){

        info = username+" 说: "+info;

        try{
            client.write(ByteBuffer.wrap(info.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //读取从服务器端回复的消息
    public void readInfo(){

        try {
            //查看通道中是否有事件发生
            int readChannel = selector.select();
            if(readChannel>0){
                //即selector中存在有事件发生的通道
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                while (keyIterator.hasNext()){
                    SelectionKey key = keyIterator.next();
                    //判断
                    if(key.isReadable()){
                        //得到相关通道
                        SocketChannel client = (SocketChannel) key.channel();
                        //得到一个buffer
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        //在通道中的数据读到buffer中
                        client.read(byteBuffer);
                        //将得到的数据转成字符串
                        String message = new String(byteBuffer.array());
                        System.out.println(message.trim());
                    }

                    //需要将这个东西移除掉，不然会出现重复读问题
                    //删除当前的selectionKey
                    keyIterator.remove();
                }
            }
            else {
               // System.out.println("没有可以用的通道");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        //先启动客户端
        GroupChetClient chatClient = new GroupChetClient();

        //启动一个线程来进行这些工作
        ExecutorService pool = Executors.newFixedThreadPool(10);
        //每隔三秒，读取从服务器端发送的数据
        pool.execute(()->{
            while (true){
                //使用一个线程进行信息的读取
                chatClient.readInfo();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //循环读取客户端发送的信息
        System.out.println("请输入:");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String message = scanner.nextLine();
            chatClient.sendInfo(message);
        }
    }
}
