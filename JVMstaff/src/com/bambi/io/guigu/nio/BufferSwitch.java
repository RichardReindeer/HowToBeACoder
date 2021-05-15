package com.bambi.io.guigu.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * 将普通Buffer转换成只读buffer
 */
public class BufferSwitch {
    public static void main(String[] args) {
        //创建一个直内存缓冲
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

        for(int i =0;i<1024;i++){
            byteBuffer.put((byte) i);
        }
        byteBuffer.flip();
        //得到一个readOnlyBuffer
        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();

        //readOnlyBuffer只能读，不能写入
        readOnlyBuffer.put((byte) 1);

    }
}
