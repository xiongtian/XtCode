package com.xtservlet.user;


import com.xtservlet.core.Request;
import com.xtservlet.core.Response;
import com.xtservlet.core.Servlet;

public class RegisterServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        // 关注了内容
        response.print("注册成功！");
    }
}
