package com.offcn.NIO.file;


import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

//通过NIO实现文件IO
public class TestFileNIO {

    @Test  // 往本地文件写数据
    public void test1() throws IOException {
        //1.创建输出流
        FileOutputStream fileOutputStream = new FileOutputStream("aa.txt");
        //2.从流中获取一个通道
        FileChannel channel = fileOutputStream.getChannel();

        //3.提供一个初始容量为1024的缓冲区 ,  allocate(初始容量)
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //4.往缓冲区中春如数据
        String str = "helloworld!!!NIO";
        buffer.put(str.getBytes()); //提供一个字节数组
        //5.先翻转缓冲区 , 再把缓冲区的数据写入通道中
        buffer.flip();
        channel.write(buffer);
        //6. 关闭
        fileOutputStream.close();

    }

    @Test  // 从本地文件读数据
    public void test2() throws IOException {

        File file = new File("D:\\若依项目\\wisdom-education\\db\\education.sql"); // 获取文件的内容大小  length

        //1.创建输入流 是为了 创建通道
        FileInputStream inputStream = new FileInputStream(file);
        //2.从流中获取一个通道
        FileChannel channel = inputStream.getChannel();

        //3.提供一个初始容量为1024的缓冲区 ,  allocate(初始容量)
        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());

        //4. 从通道中读取数据放入buffer
        channel.read(buffer);

        //把字节数组转成字符串  ,  buffer.array()把 缓冲区里的数据转成字节数组
        System.out.println(new String(buffer.array()));

        //5.关闭
        inputStream.close();

    }

    @Test // 使用NIO实现文件复制
    public  void test3 () throws Exception {
        //1.创建两个流
        File file = new File("D:\\图片专用\\初音未来.jpg");

        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream("G:\\price.jpg");

        //2.创建通道
        FileChannel sourceFC = fis.getChannel(); // 输入通道 文件源在里面
        FileChannel destFC = fos.getChannel(); //输出通道

        //3.复制
        destFC.transferFrom(sourceFC,0,sourceFC.size()); // 将有数据源 的通道数据复制到当前的同道

        //4.关流
        fis.close();
        fos.close();
    }


}
