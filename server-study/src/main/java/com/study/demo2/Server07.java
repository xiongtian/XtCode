package com.study.demo2;




import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 目标：整合配置文件
 *
 * */
public class Server07 {

    private ServerSocket serverSocket;

    public static void main(String[] args) throws InterruptedException {
        Server07 server01 = new Server07();
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
    public void recieve() {
        try {
            Socket client = serverSocket.accept();
            System.out.println("一个客户端建立了连接！");

            //获取请求协议
            Request request = new Request(client);

            //获取响应协议
            Response response = new Response(client);
            Servlet servlet = WebApp.getServletFromUrl(request.getUrl());

            if (null!=servlet) {
                servlet.service(request,response);
            }else {

                // 错误
                response.pushToBrowser(404);
            }
         /*
         在WebApp里面进行处理了
         if (request.getUrl().equals("login")){
                servlet = new LoginServlet();
            }else if (request.getUrl().equals("reg")){
                servlet = new RegisterServlet();
            } else {
                // 首页
            }*/
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
