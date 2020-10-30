package com.xtservlet.core;

/**
 *
 * 服务器小脚本接口
 * @author xiongtian
 * @create 2020/9/26-22:49
 */
public interface Servlet  {
    void service(Request request, Response response);
    // 简化 这里就不具体展开
    /*void doGet(Request request,Response response);
    void doPost(Request request,Response response);*/
}
