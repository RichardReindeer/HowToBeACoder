package com.bambi.io.guigu.nio.zeroCopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 传统JAVA IO 客户端
 */
public class OldClient {

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 7001);
        String fileName = "28.png";
        //创建文件输入流对象
        FileInputStream fileInputStream = new FileInputStream(fileName);

        //创建文件输出流对象
        DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());

        //指定byte数组大小
        byte[] buffer = new byte[2048];
        long readCount;
        long total = 0;

        //计时测试时间
        long startTime = System.currentTimeMillis();

        while ((readCount = fileInputStream.read(buffer)) != -1) {
            total += readCount;
            dataOutputStream.write(buffer);
        }
        System.out.println("发送的总字节输为:" + total + ",耗时" + (System.currentTimeMillis() - startTime));

        //记得流关闭!!!!!!!!!
        dataOutputStream.close();
        client.close();
        fileInputStream.close();
    }
}
