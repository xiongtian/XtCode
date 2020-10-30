package com.xtservlet.core;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 目标：处理404 505和首页
 *
 * */
public class Server {

    private ServerSocket serverSocket;
    private boolean isRunning;

    public static void main(String[] args) throws InterruptedException {
        Server server01 = new Server();
        server01.start();
    }

    // 启动服务
    public void start() {
        try {
            serverSocket = new ServerSocket(5555);
            serverSocket.setSoTimeout(0);
            isRunning = true;
            recieve();
            System.out.println(serverSocket.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败....");
            stop();
        }
    }

    // 接收连接处理
    public void recieve() {
        // 进行循环
        while (isRunning) {
            try {
                Socket client = serverSocket.accept();
                System.out.println("一个客户端建立了连接！");
                // 多线程处理
                new Thread(new DisPatcher(client)).start();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("客户端错误，连接出现了问题....");
            }
        }
    }

    // 停止服务
    public void stop() {
        isRunning = false;
        try {
            this.serverSocket.close();
            System.out.println("服务器已停止！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
