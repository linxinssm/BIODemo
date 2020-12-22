package com.offcn.NIO.wangluo;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

//NIO网络  客户端
public class WLNIOClient {

    public static void main(String[] args) throws Exception {
        //1. 获取网络通道
        SocketChannel channel = SocketChannel.open();

        //2. 设置阻塞方式 : 阻塞式  : true ; 非阻塞 true
        channel.configureBlocking(false);

        //3.提供服务器端的IP地址和端口号  封装成InetSocketAddress
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",9999);

        //4.连接服务端
       if( !channel.connect(inetSocketAddress)){
          //当连接失败 , 则换finishConnect继续连接
           while (!channel.finishConnect()){ //nio作为非阻塞的优势
               System.out.println("客户端 , 可以 继续做其他事不用管连接失败, 不会阻塞");
          }
       }

       //5.获取缓冲区存入数据 当连接成功后发送数据

        String str = " 你好啊, 最近好吗 ?";

        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());

        //6.发送数据
        channel.write(buffer);

        System.in.read(); //阻止程序结束  不然服务端会报错

    }
}
