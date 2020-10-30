package com.study.demo1;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author xiongtian
 * @create 2020/9/26-16:49
 */
public class Request1 {

    // 协议信息
    private String requestInfo;

    // 请求的参数
    // 请求的方式
    private String method;
    // 请求的url
    private String url;
    // 请求的参数
    private String queryStr;

    // 拼接头信息时需要的常量
    private final String BLANK = " ";
    private final String CRLF = "\r\n";

    public Request1(InputStream is) {

        byte[] datas = new byte[1024 * 1024];
        int len;
        try {
            len = is.read(datas);
            this.requestInfo = new String(datas, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // 分解字符串
        parseRequestInfo();
    }

    public Request1(Socket client) throws IOException {
        this(client.getInputStream());
    }

    private void parseRequestInfo() {
        System.out.println("-------------- 分解开始--------------");
        System.out.println(requestInfo);
        System.out.println("-----1、获取请求方式:开头到一个/-----");
        method = this.requestInfo.substring(0, this.requestInfo.indexOf("/")).trim().toLowerCase();
        System.out.println(method);
        System.out.println("-----2、获取请求的url:第一个 / 到 HTTP/ -----");
        System.out.println("-----可能包含请求参数？前面的为url ----");
        // 1)、获取第一个 / 的位置
        int startIndex = this.requestInfo.indexOf("/")+1;
        // 2)、获取去 HTTP/ 的位置
        int endIndex = this.requestInfo.indexOf("HTTP/");
        // 3)、截取
        this.url = this.requestInfo.substring(startIndex, endIndex).trim();
        System.out.println(url);
        // 4)、获取？的位置
        int queryInx = this.url.indexOf("?");
        if (queryInx>=0) {// 表示请求参数存在
            String[] urlArray = this.url.split("\\?");
            this.url=urlArray[0];
            queryStr= urlArray[1];
        }
        System.out.println("地址:"+url+"\n参数:"+queryStr);

        System.out.println("-----3、获取请求参数：如果Get已经获取，如果是post可能在请求体中 ----");

        if (method.equals("post")) {
            String qStr = this.requestInfo.substring(this.requestInfo.lastIndexOf(CRLF)).trim();
            if (null==queryStr) {
                queryStr = qStr;
            }else {
                queryStr +="&"+qStr;
            }
        }
        queryStr=null==queryStr?"":queryStr;
        System.out.println(method+"-->"+url+"-->"+queryStr);
    }
}
