package com.xiongtian.springboot.controller;


import com.xiongtian.springboot.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class HelloController {

    /*@RequestMapping({"/","/index.html"})
    public String index(){
        return "index";
    }*/

    @ResponseBody
    @RequestMapping(value = "/hello")
    public String hello(@RequestParam("user") String user){
        if (user.equals("aaa")) {
            throw new UserNotExistException();
        }
        return "hello SpringBoot!";
    }

    // 查出一些数据在界面展示
    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        // classpath:/templates/success.html
        map.put("hello","你好");
        return "success";
    }
}
