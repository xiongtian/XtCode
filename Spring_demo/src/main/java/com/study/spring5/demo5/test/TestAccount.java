package com.study.spring5.demo5.test;

import com.study.spring5.demo5.config.TxConfig;
import com.study.spring5.demo5.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xiongtian
 * @create 2020/9/5-23:48
 */
public class TestAccount {


    @Test
    public void test01() {

        ApplicationContext context = new ClassPathXmlApplicationContext("bean9.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.accountMoney();
    }

    @Test
    public void test02() {

        ApplicationContext context = new ClassPathXmlApplicationContext("bean10.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.accountMoney();
    }

    @Test
    public void test03() {

        ApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);
        UserService userService = context.getBean("userService", UserService.class);
        userService.accountMoney();
    }
}
