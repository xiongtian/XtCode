package com.study.spring5.demo5.service;

import com.study.spring5.demo5.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiongtian
 * @create 2020/9/5-23:49
 */
@Service
@Transactional(readOnly = false,timeout = -1,propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
public class UserService {
    // 注入dao
    @Autowired
    @Qualifier(value = "myUserDao")
    private UserDao userDao;


    // 编程式
//    // 转账的方法
//    public void accountMoney() {
//
//        try{
//            // 第二步：开启事务
//
//            // 第二步：进行业务操作
//            // lucy 少一百
//            userDao.reduceMoney();
//
//            // 模拟异常
//            int i = 10 / 0;
//
//            // Marry 多一百
//            userDao.addMoney();
//
//            // 第三步：没有异常，提交事务
//        }catch (Exception exception) {
//
//            // 第四步：出现异常，进行事务的回滚
//        }
//
//    }
    // 声明式
    public void accountMoney() {


        // lucy 少一百
        userDao.reduceMoney();

        // 模拟异常
        int i = 10 / 0;

        // Marry 多一百
        userDao.addMoney();


    }
}
