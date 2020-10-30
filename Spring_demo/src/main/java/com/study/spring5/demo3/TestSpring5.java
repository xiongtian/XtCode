package com.study.spring5.demo3;

import com.study.spring5.demo3.config.SpringConfig;
import com.study.spring5.demo3.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestSpring5 {

    @Test
    public void test01() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean7.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.add();
        /*
         * 输出结果：
         * service add!..........
         * */
    }

    /*全注解开发*/
    @Test
    public void test02() {

        // 注解开发
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = context.getBean("userService", UserService.class);
        userService.add();
        /*输出结果：
        service add!..........
        dao add
        abc*/
    }
}
