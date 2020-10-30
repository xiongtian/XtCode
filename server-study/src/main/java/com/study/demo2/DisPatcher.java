package com.study.demo2;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 分发器：对404 505 状态进行处理
 * @author xiongtian
 * @create 2020/9/27-0:10
 */
public class DisPatcher implements  Runnable{
    private Socket client;
    private Request request;
    private Response response;
    public DisPatcher(Socket client) {
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
        InputStream is=null;

        try {
            if (null==request.getUrl() || request.getUrl().equals("")){
                // 首页
                is = Thread.currentThread().getContextClassLoader().getResourceAsStream("index.html");
                response.print(process(is,"utf-8"));
                response.pushToBrowser(200);
                is.close();
                this.release();
                return;
            }
            Servlet servlet = WebApp.getServletFromUrl(request.getUrl());

            if (null != servlet) {
                servlet.service(request, response);
            } else {
                // 错误
                is = Thread.currentThread().getContextClassLoader().getResourceAsStream("error.html");
                response.print(process(is,"utf-8"));
                response.pushToBrowser(404);
                is.close();
            }
            // 关注状态码
            response.pushToBrowser(200);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                response.print("你好我不好，我马上会好！");
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

    /*
    *从字节流读取数据
    * */
    public String process(InputStream in, String charset) {
        byte[] buf = new byte[1024];
        StringBuffer sb = new StringBuffer();
        int len = 0;
        try {
            while ((len=in.read(buf)) != -1) {
                sb.append(new String(buf, 0, len, charset));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
