package com.study.spring5.demo1.test;


import com.study.spring5.demo1.Book;
import com.study.spring5.demo1.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 入门案例的测试f
 * <p>
 * DI:依赖注入，就是注入属性
 *
 * @author xiongtian
 * @create 2020/8/31-22:16
 */
public class TestSpring5 {

    @Test
    public void testAdd() {
        /*
        入门案例的代码
        */
        // 1、加载Spring的配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        // 2、获取配置创建的对象
        User user = context.getBean("user", User.class);
        System.out.println(user);
        user.add();

        // 属性注入的代码
        Book book1 = context.getBean("book1", Book.class);
        System.out.println(book1);

        // 构造函数注入
        Book book2 = context.getBean("book2", Book.class);
        System.out.println(book2);

        // 使用p名称空间
        Book book3 = context.getBean("book3", Book.class);
        System.out.println(book3);

        /*
        * 输出
        *
        *   com.study.spring5.demo1.User@3891771e
            add......
            Book{name='hahah', author='小萌'}
            Book{name='《java从入门到入狱》', author='小熊'}
            Book{name='《九阳豆浆机》', author='小熊熊'}*/

        /*设置空值的*/
        Book book4 = context.getBean("book4", Book.class);
        System.out.println(book4);
        /*输出： Book{name='null', author='小萌'} * */


        /*包含特殊符号： <> */
        Book book5 = context.getBean("book5", Book.class);
        System.out.println(book5);
        /*输出结果：Book{name='<<南京>>', author='小萌'}*/
    }
}
