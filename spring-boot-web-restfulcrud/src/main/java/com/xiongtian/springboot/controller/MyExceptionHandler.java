package com.xiongtian.springboot.controller;

import com.xiongtian.springboot.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
/*
* 自定义效果的json数据
* */
@ControllerAdvice // SpringMVC的异常处理器需要的标签
public class MyExceptionHandler {

    /**
     * 返回的数据不管是浏览器还是请求都是json
     * @param e
     * @return
     */
   /* @ResponseBody
    @ExceptionHandler(UserNotExistException.class) // 指定处理的异常
    public Map<String, Object> handlerException(Exception e) {
        Map<String, Object> map=new HashMap<>();
        map.put("code","user.notexist");
        map.put("message",e.getMessage());
        return map;

    }*/

    @ExceptionHandler(UserNotExistException.class) // 指定处理的异常
    public String handlerException(Exception e, HttpServletRequest request) {
        Map<String, Object> map=new HashMap<String, Object>();
        // 传入我们自己的错误状态码 4XX 5XX
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notexist");
        map.put("message", e.getMessage());

        request.setAttribute("ext",map);
        // 转发到/error BasicErrorController会进行自适应处理
        return "forward:/error";

    }
}
