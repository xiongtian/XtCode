package com.study.demo1;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/*
* 目标：返回响应
* */
public class Server02 {

    private ServerSocket serverSocket;

    public static void main(String[] args) throws InterruptedException {
        Server02 server01 = new Server02();
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

            StringBuilder context =new StringBuilder();
            context.append("<html>");
            context.append("<head>");
            context.append("<title>");
            context.append("服务器响应成功！");
            context.append("</title>");
            context.append("</head>");
            context.append("<body>");
            context.append("终于回来了！");
            context.append("</body>");
            context.append("</html>");
            int size=context.toString().getBytes().length;
            StringBuilder responseInfo = new StringBuilder();
            String blank =" ";
            String CRLF="\r\n";


            // 返回响应
            // 1、状态行： HTTP/1.1 200 OK
            responseInfo.append("HTTP/1.1").append(blank);
            responseInfo.append(200).append(blank);
            responseInfo.append("OK").append(CRLF);

            // 2、响应头：（最后一行存在空行）
            /*
			 Date:Mon,31Dec209904:25:57GMT
			Server:shsxt Server/0.0.1;charset=GBK
			Content-type:text/html
			Content-length:39725426
			 */
            responseInfo.append("Date:").append(new Date()).append(CRLF);
            responseInfo.append("Server:").append("shsxt Server/0.0.1;charset=GBK").append(CRLF);
            responseInfo.append("Content-type:").append("text/html").append(CRLF);
            responseInfo.append("Content-length:").append(size).append(CRLF);
            // 空行
            responseInfo.append(CRLF);

            // 3、正文
            responseInfo.append(context.toString());

            // 写到客户端
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bw.write(responseInfo.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端错误，连接出现了问题....");
        }
    }

    // 停止服务
    public void stop() {

    }
}
