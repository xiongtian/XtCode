package com.study.spring5.demo1.Service;

import com.study.spring5.demo1.dao.UserDao;
import com.study.spring5.demo1.dao.UserDaoImpl;

public class UserService {

     // 1)创建UserDao类型的属性，生成set方法
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add() {
        System.out.println("service add ...............");
        // 1、原始方式
        // 创建userDao的对象
        UserDao userDao = new UserDaoImpl();
        userDao.update();

        // 2、使用spring

    }

}
