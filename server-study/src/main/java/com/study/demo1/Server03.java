package com.study.demo1;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/*
* 目标：封装响应信息
* 1、内容可以动态添加
* 2.关注状态码，拼接好响应的协议信息
* */
public class Server03 {

    private ServerSocket serverSocket;

    public static void main(String[] args) throws InterruptedException {
        Server03 server01 = new Server03();
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

            Response response =new Response(client);

            // 关注了内容
            response.print("<html>");
            response.print("<head>");
            response.print("<title>");
            response.print("服务器响应成功！");
            response.print("</title>");
            response.print("</head>");
            response.print("<body>");
            response.print("终于回来了2！");
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
