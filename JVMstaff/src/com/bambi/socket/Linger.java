package com.bambi.socket;

/**
 * 当socket调用close方法后，socket其实并不会立即关闭】
 * 而是会等待一段时间，将缓冲池中的数据发送完毕后再关闭
 * void setSoLinger(boolean on , int linger ) 方法的作用是 启动/禁用具有指定逗留时间的so_Linger 最大超时值是特定于平台的
 */
public class Linger {
}
