package com.study.demo2;

/**
 * @author xiongtian
 * @create 2020/9/26-23:46
 */
public class OtherServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        response.print("其他测试界面");
    }
}
