package com.bambi.io.guigu.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Nio
 * FileChannel 文件通道
 * 现在使用一个Buffer来进行文件的读取
 *
 * file.txt --->  FileInputStream(FileChannel) --->  Buffer ---> FileOutputStream(FileChannel) ---> newFile.txt
 */
public class FileChannelDemo03 {
    public static void main(String[] args) throws Exception{
        //拷贝文件 Nio.txt
        FileInputStream fileInputStream = new FileInputStream("Nio.txt");
        //获取文件输入流对象
        FileChannel fileChannel = fileInputStream.getChannel();

        //文件输出流对象
        FileOutputStream fileOutputStream = new FileOutputStream("NioCopy.txt");
        FileChannel fileChannelOut = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        while (true){

            //clear()的作用为将四个标志属性复位
            //如果不进行复位，在一次读写操作结束后，postion 将 = limit , 两者之间没有元素
            //读取方法返回的read就等于0，永远不会等于-1，会进入死循环
            byteBuffer.clear();
            /**
             * public Buffer clear() {
             *         position = 0;
             *         limit = capacity;
             *         mark = -1;
             *         return this;
             *     }
             */
            int read = fileChannel.read(byteBuffer);
            if(read==-1){
                //表示读取完毕，则退出循环
                break;
            }
            //进行反转，将读操作改为写操作
            byteBuffer.flip();
            fileChannelOut.write(byteBuffer);
        }

        fileOutputStream.close();
        fileInputStream.close();
        fileChannel.close();
        fileChannelOut.close();

    }
}
