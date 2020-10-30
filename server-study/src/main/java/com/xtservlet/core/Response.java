package com.xtservlet.core;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

/**
 * @author xiongtian
 * @create 2020/9/26-11:31
 */
public class Response {

    private BufferedWriter bw;

    //正文
    private StringBuilder context;

    private int len;//正文的字节数

    // 协议头信息(状态行与请求头 回车)
    private StringBuilder headInfo;


    // 拼接头信息时需要的常量
    private final String BLANK = " ";
    private final String CRLF = "\r\n";


    private Response() {
        context = new StringBuilder();
        headInfo = new StringBuilder();
        len = 0;
    }

    public Response(Socket client) {
        this();
        try {
            bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            headInfo = null;
        }
    }

    public Response(OutputStream os) {
        this();
        bw = new BufferedWriter(new OutputStreamWriter(os));
    }


    // 构建头信息
    private void createHeadInfo(int code) {

        String info = null;

        // 1、响应行：HTTP/1.1 200 OK
        headInfo.append("HTTP/1.1").append(BLANK);
        headInfo.append(code).append(BLANK);
        switch (code) {
            case 200:
                info = "OK";
                break;
            case 404:
                info = "NOT FOUND";
                break;
            case 505:
                info = "SERVER ERROR";
                break;
            default:
                info = "DEFULT INFO";
                break;
        }
        headInfo.append(info).append(CRLF);

        //2、响应头（最后一行存在空行）
        headInfo.append("Date:").append(new Date()).append(CRLF);
        headInfo.append("Server:").append("shsxt Server/0.0.1;charset=GBK").append(CRLF);
        headInfo.append("Content-type:").append("text/html").append(CRLF);
        headInfo.append("Content-length:").append(len).append(CRLF);
        // 空行
        headInfo.append(CRLF);
    }

    // 动态添加内容
    public Response print(String info) {
        context.append(info);
        len += info.getBytes().length;
        return this;
    }

    public Response println(String info) {
        context.append(info).append(CRLF);
        len += (info + CRLF).getBytes().length;
        return this;
    }

    // 推送响应信息
    public void pushToBrowser(int code) throws IOException {

        if (null == headInfo) {
            code = 505;
        }
        createHeadInfo(code);

        bw.append(headInfo);
        bw.append(context);
        bw.flush();

    }
}
