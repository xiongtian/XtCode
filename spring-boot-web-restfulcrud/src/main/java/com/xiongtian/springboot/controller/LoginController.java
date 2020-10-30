package com.xiongtian.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @PostMapping(value = "/user/login")
    public String Login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){

        if (!StringUtils.isEmpty(username) && "123456".equals(password)){
            // 登录成功 防止表单提交，可以重定向到主页
            session.setAttribute("loginUser",username);
            return "redirect:/main.html ";
        }else {
            // 登录失败
            map.put("msg","用户名或者密码错误！");
            return "login";
        }
    }
}
