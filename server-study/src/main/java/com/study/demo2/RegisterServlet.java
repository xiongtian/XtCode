package com.study.demo2;


public class RegisterServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        // 关注了内容
        response.print("注册成功！");
    }
}
