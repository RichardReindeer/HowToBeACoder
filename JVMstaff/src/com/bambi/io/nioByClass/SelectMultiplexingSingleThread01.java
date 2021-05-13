package com.bambi.io.nioByClass;

import java.io.BufferedReader;
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
 * 关于Selector
 * <p>
 * 用java实现多路复用器
 * 多路复用器其实是kernel提供的，种类很多，
 * java的selector如何包装的?
 */
public class SelectMultiplexingSingleThread01 {

    private ServerSocketChannel serverSocketChannel = null;
    private Selector selector = null;//linux 多路复用器(select poll  epoll kqueue) nginx event{}
    int port = 9090;

    public void initServer() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(port));

            //如果再epoll的模型下，open---> epoll create --->fd3
            selector = Selector.open();//select poll * poll 优先选择 epoll 但是可以 -D修正
            //内核中 epoll 开辟空间 ， select poll 不开辟空间
            //两个空间 ， 1.c语言实现的jvm虚拟机空间
            //          2. java 代码空间


            /**
             * register
             * 如果:
             * select , poll : jvm中开辟一个数组，fd4放进去
             * epoll, epoll_ctl (fd3,ADD,fd4,EpollIn)
             */
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        initServer();
        System.out.println("服务器启动了");
        try {
            while (true) {
                Set<SelectionKey> keys = selector.keys();
                System.out.println("size:" + keys.size());

                /**
                 * 1.多路复用器(select , poll or epoll (epoll_wait)
                 * select()是什么意思?
                 * 1.select , poll 其实 内核的select (fd4) poll (fd4)
                 * 2. epoll 其实内核 --> epoll_wait()
                 * *,参数可以带时间，没有时间0 , 阻塞，有时间可以设置一个超时
                 * selector.wakeup()  结果返回0
                 *
                 * 懒加载:
                 * 其实再触碰到selector.select() 调用的时候触发了epoll_ctl的调用
                 *
                 * select()约等于询问内核哪个socket有事件
                 */
                //完成了内核到jvm空间的一个事件拷贝
                //会将到达事件的副本拷贝到jvm中
                while (selector.select() > 0) {
                    //是从jvm空间把有事件的集合拷贝到java程序员手中
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();//返回的有状态的fd集合
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    /**
                     * 不管什么多路复用器，都只能返回状态，然后自己去处理读写操作，十分麻烦
                     * Nio 自己要对每一个fa调用系统调用，浪费资源，但是这里面指带哦用了一次select方法
                     */
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        /**
                         * 此处remove()的是jvm空间中的数据以及返回的集合中的数据
                         * 如果不remove的后果:
                         * 字节码调用方法的时候先问的是jvm，
                         * jvm在去访问内核
                         * 如果内核中数据已经读完，但是jvm中的数据没有remove，
                         * 再去读取还是先读取jvm中的拷贝，
                         * jvm虽然会向内核中询问，但是只会进行追加操作，不会进行真正的同步操作，
                         * 副本拷贝set集合中的数据依旧存在
                         */
                        iterator.remove();//set , 不移除会重复循环处理
                        if (key.isAcceptable()) {
                            //看代码的时候，此处是重点，如果要去接收一个新的连接
                            //语义上，accept接收的是一个连接且返回新连接的fd对吧
                            // 那新的fd呢?b
                            acceptHandler(key);
                        } else if (key.isReadable()) {
                            readHandler(key);
                        }

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 因为epoll 也是一个同步操作
     * 只是负责开辟一个存放socket连接的空间，想要调用这些数据还是需要自己去调用内核调用
     * 所以还是需要重新获取?
     * @param key
     * @throws IOException
     */
    public void acceptHandler(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel client = serverSocketChannel.accept(); //目的是调用accept接收的客户端
        client.configureBlocking(false);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1092);
        /**
         * 重新注册到多路复用器上，
         * 调用register
         * epoll : 调用epoll_ctl(fd3,add,fd7,epollIn)
         * poll select :在jvm中开辟一个数组，将fd7放进去
         */
        client.register(selector,SelectionKey.OP_READ,byteBuffer);
    }

    public void readHandler(SelectionKey key){
        SocketChannel socketChannel = (SocketChannel) key.channel();
        //key.attachment?
        ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
        byteBuffer.clear();
        int read = 0;
        try{
            while (true){
                read = socketChannel.read(byteBuffer);
                if(read>0){
                    byteBuffer.flip();
                    //?
                    while (byteBuffer.hasRemaining()){
                        socketChannel.write(byteBuffer);
                    }
                    byteBuffer.clear();
                }else if(read ==0){
                    break;
                }else {
                    socketChannel.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SelectMultiplexingSingleThread01 selectMultiplexingSingleThread01 = new SelectMultiplexingSingleThread01();
        selectMultiplexingSingleThread01.start();
    }
}
