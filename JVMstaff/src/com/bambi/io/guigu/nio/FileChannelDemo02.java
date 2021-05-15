package com.bambi.io.guigu.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 利用FileChannel读取数据
 */
public class FileChannelDemo02 {
    public static void main(String[] args) throws IOException {
        File file = new File("c://FileChannelDemo//day15.txt");
        //创建文件输入流
        FileInputStream fileInputStream = new FileInputStream(file);
        if(fileInputStream!=null){
            //获取Channel
            FileChannel fileChannel = fileInputStream.getChannel();
            //创建缓冲区
            //动态获取完整的文件长度，之后设置为ByteBuffer的长度
            //allocate 和allocateDirect读取数据的位置不一样
            //比如此处，使用allocateDirect读取就会报错，因为底层数组hb为空
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
            ByteBuffer.allocateDirect(1024);
            //将通道数据读入到buffer中
            fileChannel.read(byteBuffer);

            //将字节缓冲转换成字符串, 将byteBuffer
            System.out.println(new String (byteBuffer.array()));
        }
        fileInputStream.close();
    }
}
