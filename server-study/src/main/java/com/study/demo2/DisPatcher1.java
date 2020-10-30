package com.study.demo2;

import java.io.IOException;
import java.net.Socket;

/**
 * 分发器
 * @author xiongtian
 * @create 2020/9/27-0:10
 */
public class DisPatcher1 implements  Runnable{
    private Socket client;
    private Request request;
    private Response response;
    public DisPatcher1(Socket client) {
        this.client=client;
        try {
            //获取请求协议
            request = new Request(client);
            //获取响应协议
            response = new Response(client);
        } catch (IOException e) {
            e.printStackTrace();
            this.release();
        }

    }

    @Override
    public void run() {
        try {
            Servlet servlet = WebApp.getServletFromUrl(request.getUrl());

            if (null != servlet) {
                servlet.service(request, response);
            } else {
                // 错误
                response.pushToBrowser(404);
            }

            // 关注状态码
            response.pushToBrowser(200);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                response.pushToBrowser(500);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        //释放资源
        this.release();
    }

    /**
     * 释放资源
     */
    private void release(){
        try {
            client.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
