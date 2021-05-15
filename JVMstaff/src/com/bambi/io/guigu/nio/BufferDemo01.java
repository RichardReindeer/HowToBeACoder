package com.bambi.io.guigu.nio;

import java.nio.*;

/**
 * 对Buffer主要使用方法进行测试
 */
public class BufferDemo01 {
    public static void main(String[] args) {
        /**
         * nio包中的buffer 有除boolean之外7种基本类型的相关包装子类，
         */
        CharBuffer charBuffer = CharBuffer.allocate(10);
        IntBuffer intBuffer = IntBuffer.allocate(10);
        ShortBuffer shortBuffer = ShortBuffer.allocate(10);
        LongBuffer longBuffer = LongBuffer.allocate(10);
        FloatBuffer floatBuffer = FloatBuffer.allocate(10);
        DoubleBuffer doubleBuffer = DoubleBuffer.allocate(10);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10);

        for(int i = 0;i<byteBuffer.limit();i++){
            byteBuffer.put((byte) i);
        }
        /**
         * 常用方法
         */

        //返回缓存区容量
        int capacity = charBuffer.capacity();
        //返回目前缓冲区中position的位置
        int position = byteBuffer.position();
        //移动到指定位置  并返回该位置的元素
        byteBuffer.position(2);
        System.out.println(byteBuffer.position());
        //返回缓冲区的限制
        int limit = byteBuffer.limit();
        //设置limit
        byteBuffer.limit(10);
        //清除此缓冲区，数据不清除，只是将各个标记回复到初始状态
        byteBuffer.clear();
        System.out.println(byteBuffer.position());
        //反转缓冲区
        byteBuffer.flip();
        System.out.println(byteBuffer.position());
        //告知此缓冲区是否是只读缓冲区
        boolean readOnly = byteBuffer.isReadOnly();
        System.out.println(readOnly);
        //判断byteBuffer中position和limit之间是否还存在元素
        boolean hasRemaing = byteBuffer.hasRemaining();
        System.out.println(hasRemaing);
    }
}
