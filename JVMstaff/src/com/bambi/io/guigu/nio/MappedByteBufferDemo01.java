package com.bambi.io.guigu.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Nio 提供了MappedByteBuffer  支持直接将文件在缓存中修改
 *
 * 说明:
 * 1.可以直接让文件在内存中修改，操作系统不需要再次拷贝一次
 * 2.这个内存指的是堆外内存
 */
public class MappedByteBufferDemo01 {
    public static void main(String[] args) throws IOException {
        //创建RandomAccessFile 支持读写操作
        RandomAccessFile randomAccessFile = new RandomAccessFile("Nio.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        /**
         * map()方法的参数:
         * 1.FileChannel.MapMode  选择Map的操作模式
         *   底层静态常量是字符串
         * 2. 0 可以支持修改的起始位置
         * 3. size: 是映射到直接内存的大小，
         * 允许修改的范围则为 size - position
         * 底层Buffer的类型是DirectBuffer 直接内存缓冲
         * 因为直接在操作系统内存中操作，所以速度更快，且减少了一次拷贝
         */
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,10);
        //将第一个字节改为H
        mappedByteBuffer.put(0, (byte) 'H');
        //如果指定的index大于size，则会爆出下标越界的异常
    }
}
