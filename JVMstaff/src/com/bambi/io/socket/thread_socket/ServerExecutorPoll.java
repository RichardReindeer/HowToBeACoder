package com.bambi.io.socket.thread_socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 使用线程池的服务器端
 */
public class ServerExecutorPoll {

    private ServerSocket server;
    private Executor pool;

    /**
     * 使用构造方法申请端口号
     * 并设置线程池大小
     *
     * @param port
     * @param poolSize
     * @throws IOException
     */
    public ServerExecutorPoll(int port, int poolSize) throws IOException {
        System.out.println("注册服务器");
        server = new ServerSocket(port);
        System.out.println("服务器注册成功");
        pool = Executors.newFixedThreadPool(poolSize);
    }

    /**
     * 启动服务器
     */
    public void startService() throws IOException {
        for(;;){
            Socket socket = server.accept();
            //将接收到的客户端当作参数传入到ReadRunnable的构造方法中
            pool.execute(new ReadRunnable(socket));
        }
    }

    public static void main(String[] args) throws IOException {
        //在实例化的时候定义端口号和线程池大小
        //Executor 可以创建多种类型的线程池,
        //其中FixedThreadPool为固定大小的线程池，
        //适合服务器使用
        ServerExecutorPoll serverExecutorPoll = new ServerExecutorPoll(8088,1000);
        serverExecutorPoll.startService();
    }
}


class ReadRunnable implements Runnable {
    private Socket socket;

    public ReadRunnable(Socket socket) {
        this.socket = socket;
    }

    /**
     * 接收客户端输入的线程
     */
    @Override
    public void run() {
        try {
            /**
             * 需要注意:
             * InputStream的读操作read()可以传入byte[] 数组
             * 而InputStreamReader的read()操作不能传入byte , 只能传入一个charArray[];
             */
            InputStream inputStream = socket.getInputStream();
            byte[] data = new byte[1024];
            //read方法返回的是读取到的字节长度
            int readLength = inputStream.read(data);
            while (readLength!=-1){
                //String(byte[] , int , int)
                //表示从第一个int为下标的位置开始，读取第二个int长度的byte数组，并转换成字符串
                String message = new String(data,0,readLength);
                System.out.println("读取到的客户端发送过来的信息是:"+message);
                //继续读取，将readLength刷新，避免死循环
                readLength = inputStream.read(data);
            }

            //流使用完之后需关闭
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}