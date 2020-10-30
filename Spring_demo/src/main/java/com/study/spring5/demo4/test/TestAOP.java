package com.study.spring5.demo4.test;

import com.study.spring5.demo4.aop.ann.User;
import com.study.spring5.demo4.aop.ann.UserProxy;
import com.study.spring5.demo4.aop.xml.Book;
import org.aspectj.lang.annotation.Around;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xiongtian
 * @create 2020/9/5-18:10
 */
public class TestAOP {

    @Test
    public void testAnno() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean8.xml");
        User user = context.getBean("user", User.class);

        user.add();

       /*输出结果（无异常的情况）：
        Around：环绕之前：
        before....
        add.........
        Around：环绕之后：
        after
        AfterReturning*/

        /*输出结果（有异常的情况）：
        Around：环绕之前：
        before....
        after
        AfterThrowing*/


        /*输出结果：（设置优先级之后）
        Around：环绕之前：
        before....
        Person Before
        add.........
        Around：环绕之后：
        after
        AfterReturning*/

    }

    @Test
    public void testXml(){
        ApplicationContext context =new ClassPathXmlApplicationContext("bean8.xml");
        Book book = context.getBean("book", Book.class);
        book.buy();
        /*输出结果：
        before ...........
        buy.....*/
    }
}
