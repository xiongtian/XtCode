package com.study.demo1;

import org.apache.commons.collections.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.*;
/*
* 封装请求协议：封装请求参数为Map
*
*
* */
/**
 * @author xiongtian
 * @create 2020/9/26-16:49
 */
public class Request2 {

    // 协议信息
    private String requestInfo;

    // 请求的参数
    // 请求的方式
    private String method;
    // 请求的url
    private String url;
    // 请求的参数
    private String queryStr;
    //存储参数
    private Map<String, List<String>> parameterMap;

    // 拼接头信息时需要的常量
    private final String BLANK = " ";
    private final String CRLF = "\r\n";

    public Request2(InputStream is) {
        parameterMap=new HashMap<>();

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

    public Request2(Socket client) throws IOException {
        this(client.getInputStream());
    }

    // 分解字符串
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
        // 转成map fav=1&fav=2&...
        convertMap();
    }

    // 处理请求参数为map
    private void convertMap() {

        // 1、分割字符串 &
        String[] keyValues = this.queryStr.split("&");
        for (String queryStr: keyValues) {
            // 2、再次分割字符串 =
            String[] kv=queryStr.split("=");
            // 保证长度为2
            kv = Arrays.copyOf(kv, 2);
            // 获取key和value
            String key = kv[0];
            String value  = kv[1]==null?null:decode(kv[1],"utf-8");
            // 存储到map中
            if (!parameterMap.containsKey(key)){
                parameterMap.put(key,new ArrayList<String>());
            }
            parameterMap.get(key).add(value);
        }
    }

    /**
     * 通过name获取对应的多个值
     * @param key
     * @return
     */
    public String[] getParameterValues(String key){
        List<String> values = this.parameterMap.get(key);

       if (CollectionUtils.isEmpty(values)) {
           return null;
       }

       return values.toArray(new String[0]);
    }


    /**
     * 通过name获取对应的单个值
     * @param key
     * @return
     */
    public String getParameterValue(String key){
        String[] values = getParameterValues(key);
        return values==null?null:values[0];
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getQueryStr() {
        return queryStr;
    }

    /**
     * 处理中文
     * @return
     */
    private String decode(String value,String enc){
        try {
            return java.net.URLDecoder.decode(value,enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
