package com.bambi.io.guigu.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 测试使用FileChannel实现文件的读写
 * 因为笔记本中没有E盘，所以只在程序中进行读写
 */
public class FileChannelBufferTest01 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("Demo01.txt");
        FileChannel fileOutChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("这是向里面写入的数据".getBytes());
        byteBuffer.flip();
        fileOutChannel.write(byteBuffer);
        byteBuffer.flip();
        FileInputStream fileInputStream = new FileInputStream("Demo01.txt");
        FileChannel fileInputChannel = fileInputStream.getChannel();
        int readLength = fileInputChannel.read(byteBuffer);
        if(readLength!=-1){
            String message = new String(byteBuffer.array()).trim();
            System.out.println("读取到的数据是:"+message);
        }
    }
}
