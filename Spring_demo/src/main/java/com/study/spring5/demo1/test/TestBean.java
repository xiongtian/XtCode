package com.study.spring5.demo1.test;

import com.study.spring5.demo1.Service.UserService;
import com.study.spring5.demo1.bean.Emp;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBean {
    @Test
    public void test(){

        // 外部bean的注入
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("bean2.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.add();
        /*
        * 输出：
        * service add ...............
        * dao update
        * */

        // 内部bean注入
        Emp emp = applicationContext.getBean("emp", Emp.class);
        emp.add();
        /*
        * 输出：
        * lucy,女,Dept{dname='安保部'}
        * */

        // 级联赋值
        /*第一种方式*/
        Emp emp1 = applicationContext.getBean("emp1", Emp.class);
        emp1.add();
        /**
         * 输出：
         * lucy,女,Dept{dname='财务部'}
         */
        /*第二种方式*/
        Emp emp2 = applicationContext.getBean("emp2", Emp.class);
        emp2.add();
        /*
        * 输出：
        * james,男,Dept{dname='技术部'}
        * */
    }


}
