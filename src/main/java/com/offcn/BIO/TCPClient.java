package com.offcn.BIO;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

//BIO客户端程序
public class TCPClient {

    public static void main(String[] args) throws IOException {
        //1.创建 ServerSocket对象
        Socket socket = new Socket("127.0.0.1",9999);//服务端IP地址

        while (true) {
            //2.从连接中取出输出流来发送消息
            OutputStream outputStream = socket.getOutputStream();
            System.out.println("请输入");

            Scanner sc = new Scanner(System.in);
            String mes = sc.nextLine();

            outputStream.write(mes.getBytes());

            //3.从连接池中取出输入流接受回话
            InputStream inputStream = socket.getInputStream(); //阻塞 服务器端没有回话就一直等待回话
            byte[] b = new byte[10]; //创建一个容器 接受流里的数据
            inputStream.read(b);

            System.out.println("老板说:" + new String(b).trim());
            //关闭
            socket.close();

        }

    }


}
