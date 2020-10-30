package com.study.demo1;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
* 目标：封装请求信息
* */
public class Server04 {

    private ServerSocket serverSocket;

    public static void main(String[] args) throws InterruptedException {
        Server04 server01 = new Server04();
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
            System.out.println("一个客户端建立了连接！");

       //获取请求协议
            Request1 request = new Request1(client);

            Response response =new Response(client);

            // 关注了内容
            response.print("<html>");
            response.print("<head>");
            response.print("<title>");
            response.print("服务器响应成功！");
            response.print("</title>");
            response.print("</head>");
            response.print("<body>");
            response.print("终于回来了3！");
            response.print("</body>");
            response.print("</html>");

            // 关注状态码
            response.pushToBrowser(200);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端错误，连接出现了问题....");
        }
    }

    // 停止服务
    public void stop() {

    }
}
