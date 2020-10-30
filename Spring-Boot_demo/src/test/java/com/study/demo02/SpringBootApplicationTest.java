package com.study.demo02;

import com.study.demo02.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
/*
* SpringBoot单元测试
* @SpringBootTest：表示这是一个SpringBoot的单元测试
* @RunWith(SpringRunner.class)：表示单元测试用Spring的驱动器来跑
*
*可以在测试期间很方便的类似编码一样进行自动注入等容器的功能
*
* */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootApplicationTest {


    @Autowired
    Person person;

    @Autowired
    ApplicationContext ioc;

    @Test
    public void contextLoads(){

        System.out.println(person);
        /*
        输出结果：
        Person{
        LastName='zhangsan',
        age=18, boss=false,
        birthday=Tue Dec 12 00:00:00 CST 2017,
        maps={k1=v1, k2=v2, k3=v3},
        list=[lisi, zhaoliu],
        dog=Dog{name='小狗', age=2}}*/
    }

    @Test
    public void testHelloService() {

        boolean flage = ioc.containsBean("helloService");
        System.out.println(flage);
    }


    // 记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    /*日志类测试
    *
    * */
    @Test
    public void testlog() {
        // 日志的级别由
        // 低到高  trace<debug<info<warn<error
        // 可以调整输出的日志级别：日志就只会在这个级别以后的高级别生效
        logger.trace("这是trace日志");
        logger.debug("这是debug日志");
        // SpringBoot默认给我们使用的是info级别的,没有指定级别的就使用springBoot的默认级别，root级别
        logger.info("这是info日志");
        logger.warn("这是warn日志");
        logger.error("这是error日志");
    }
}
