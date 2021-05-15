package com.bambi.io.guigu.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * 使用Nio的FileChannel来向文件中写入
 *
 * 一个汉字  utf-8 三个字节
 */
public class FileChannelDemo01 {
    public static void main(String[] args) throws IOException {
        String message = "还会有傻逼偷伞";

        //创建一个文件输出流
        FileOutputStream fileOutputStream = new FileOutputStream("c://FileChannelDemo//day15.txt");

        //通过文件输出流的getChannel方法获取对应通道FileOutputChannel
        //FileChannel是抽象类，看似返回的是一个FileChannel, 其实返回的是其实现类 FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();
        //创建一个字节缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        //将想要写入的数据放入缓冲区中
        byteBuffer.put(message.getBytes(StandardCharsets.UTF_8));

        //注意，此时缓冲区中的position已经指到byteBuffer的末尾，需要进行flip反转，才能进行写操作
        byteBuffer.flip();
        //将缓冲区中的数据 写入到通道中
        fileChannel.write(byteBuffer);
        System.out.println("文件写入完毕");
        fileOutputStream.close();
    }
}
