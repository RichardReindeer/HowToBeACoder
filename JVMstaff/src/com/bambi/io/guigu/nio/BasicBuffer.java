package com.bambi.io.guigu.nio;

import java.nio.Buffer;
import java.nio.IntBuffer;

/**
 * 缓冲区，NIO中十分核心的组件
 */
public class BasicBuffer {
    public static void main(String[] args) {

        //创建一个Buffer allocate = 5表示可以存放5个int对象
        IntBuffer intBuffer = IntBuffer.allocate(5);
        /*intBuffer.put(10);
        intBuffer.put(11);
        intBuffer.put(12);
        intBuffer.put(13);
        intBuffer.put(14);*/
        //intBuffer.put(15);

        //capacity() 为缓冲区的容量，不能为负，不可更改
        for(int i =0; i<intBuffer.capacity();i++){
            intBuffer.put(i);
        }

        //如何读取buffer中的数据?
        //首先需要将写buffer转换成读buffer;即将指针重新移动到缓冲区顶部 flip 读写状态切换
        intBuffer.flip();
        //hasRemaining 判断是否还有剩余未读
        while (intBuffer.hasRemaining()){
            //get方法底部维护了一个索引，每ge一次，指针就会向后移动一次
            System.out.println(intBuffer.get());
         }
    }
}
