package com.offcn.NIO.wangluo;


import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

//NIO网络  服务器端
public class WLNIOServer {

    public static void main(String[] args) throws Exception {
        //1. 获取网络通道
        ServerSocketChannel channel = ServerSocketChannel.open();

        //2. 获取selector对象 选择器
        Selector selector = Selector.open();

        //3.绑定一个端口
        channel.bind(new InetSocketAddress(9999));

        //4.设置非阻塞式
        channel.configureBlocking(false);

       //5.实现把ServerSocketChannel注册给selector对象
        channel.register(selector, SelectionKey.OP_ACCEPT); //检测客户端有没有连接

        //6 . 干活
        while (true){
            if(selector.select(2000) == 0){ //开启监控方法 返回值是有几个客户端连接着
                continue;  // 继续循环
            };
        }


    }
}
