package com.xtservlet.user;


import com.xtservlet.core.Request;
import com.xtservlet.core.Response;
import com.xtservlet.core.Servlet;

public class LoginServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        // 关注了内容
        response.print("<html>");
        response.print("<head>");
        response.print("<title>");
        response.print("第一个Servlet！");
        response.print("</title>");
        response.print("</head>");
        response.print("<body>");
        response.print("欢迎回来：" + request.getParameterValue("uname"));
        response.print("</body>");
        response.print("</html>");
    }
}
