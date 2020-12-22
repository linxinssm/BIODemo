package com.offcn.BIO;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//BIO服务器端程序
public class TCPServer {

    public static void main(String[] args) throws IOException {
        //1.创建 ServerSocket对象
        ServerSocket ss = new ServerSocket(9999);//服务器端 端口
        while (true) {

            System.out.println("进来了");
            //2. 监听客户端
            Socket accept = ss.accept();// 阻塞方法  客户端没有发送请求 就会在此处阻塞

            //3 . 从连接中取出输入流来接受消息
            InputStream is = accept.getInputStream(); //阻塞  等待客户端发的数据
            System.out.println("进来了");

            byte[] b = new byte[10];
            is.read(b);

            String clientIP = accept.getInetAddress().getHostAddress();// 获取客户端IP地址

            System.out.println(clientIP + "说:" + new String(b).trim()); //去掉字符串的首位空格

            //4. 从连接中取出输出流并会话
            OutputStream outputStream = accept.getOutputStream();
            outputStream.write("没钱".getBytes());
            //5.关流
            accept.close();
        }

    }


}
