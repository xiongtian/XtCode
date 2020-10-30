package com.study.demo1;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
/*
* 目标：使用ServerSocket建立与浏览器的连接，获取请求协议
* */
public class Server01 {

    private ServerSocket serverSocket;

    public static void main(String[] args) throws InterruptedException {
        Server01 server01 = new Server01();
        server01.start();
        server01.recieve();

    }

    // 启动服务
    public void start() {
        try {
            serverSocket = new ServerSocket(5555);
            serverSocket.setSoTimeout(0);

            System.out.println(serverSocket.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败....");
        }
    }

    // 接收连接处理
    public void recieve(){
        try {
            Socket client = serverSocket.accept();

            InputStream is= client.getInputStream();
            byte[]  datas = new byte[1024*1024];
            int len = is.read(datas);
            String requestInfo = new String(datas, 0, len);
            System.out.println(requestInfo);


            System.out.println("一个客户端建立了连接！");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端错误，连接出现了问题....");
        }
    }

    // 停止服务
    public void stop() {

    }
}
