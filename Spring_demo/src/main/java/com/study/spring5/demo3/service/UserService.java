package com.study.spring5.demo3.service;

import com.study.spring5.demo3.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

// value可以省略不写，默认值时类名称，首字母小写
@Service(value = "userService")
public class UserService {


  /*  // 定义dao类型的属性
    // 不需要添加set方法
    // 根据类型注入
    @Autowired
    @Qualifier(value = "userDaoImpl1")
    private UserDao userDao;*/


    // @Resource   // 根据类型注入
    @Resource(name = "userDaoImpl1") // 根据名称注入
    private UserDao userDao;


    @Value(value = "abc")
    private String name;

    public void add() {
        System.out.println("service add!..........");
        userDao.add();
        System.out.println(name);
    }

    /*
     * 输出结果：
     * service add!..........
     * dao add
     * abc
     * */
}
