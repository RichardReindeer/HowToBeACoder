package com.bambi.io.guigu.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 用FileChannel实现快速的文件拷贝
 */
public class FileCopyByChannelDemo01 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("01.jpg");
        FileChannel fileInputChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("01_Copy.jpg");
        FileChannel fileChannel = fileOutputStream.getChannel();

        //使用transferForm进行文件拷贝
        fileChannel.transferFrom(fileInputChannel,0,fileInputChannel.size());

    }
}
