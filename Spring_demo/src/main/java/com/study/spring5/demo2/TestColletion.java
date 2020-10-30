package com.study.spring5.demo2;

import com.study.spring5.demo2.CollectionType.Book;
import com.study.spring5.demo2.CollectionType.Course;
import com.study.spring5.demo2.CollectionType.Stu;
import com.study.spring5.demo2.autowire.Emp;
import com.study.spring5.demo2.bean.Orders;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestColletion {

    @Test
    public void test(){
        ApplicationContext context =new ClassPathXmlApplicationContext("bean3.xml");
        Stu stu = context.getBean("stu", Stu.class);
        stu.test();

        /*
        输出结果
        Stu{course=[java编程, 数据库, 高并发编程], list=[张三, 小三], map={JAVA=java, PHP=php}, set=[Redis, Mysql]}
        * */

        Book book = context.getBean("book", Book.class);
        book.test();

        /*
        * 输出结果：
        * Book{list=[人月神话, EffectiveJava, SpringCloud入门]}
         * */
        ApplicationContext context1 =new ClassPathXmlApplicationContext("bean4.xml");
        Course course = context1.getBean("myBean", Course.class);
        System.out.println(course);

        Book book1 = context1.getBean("book1", Book.class);
        Book book2 = context1.getBean("book1", Book.class);

        System.out.println(book1);
        System.out.println(book2);
        /*
        输出结果：
        * com.study.spring5.demo2.CollectionType.Book@10d59286
        * com.study.spring5.demo2.CollectionType.Book@fe18270
        * */



    }

    /*测试Spring bean的生命周期*/
    @Test
    public void test01(){
        ApplicationContext context =new ClassPathXmlApplicationContext("bean4.xml");
        Orders orders = context.getBean("orders", Orders.class);
        System.out.println("第四步 获取创建bena实例对象。");
        System.out.println(orders);

        // 手动让bean的实例销毁
        ((ClassPathXmlApplicationContext)context).close();

        /*
        * 输出结果：
        *
        * 第一步 执行无参数构造创建bean实例。
          第二步 调用set方法设置属性值。
          第三步 执行初始化的方法。
          第四步 获取创建bena实例对象。
          第五步 执行销毁方法。
        *
        * */

           /*bean的后置处理器（七步）
    *
    *     第一步 执行无参数构造创建bean实例。
          第二步 调用set方法设置属性值。
          *把 bean的实例传递给bean后置处理器的方法：postProcessBeforeInitialization
          第三步 执行初始化的方法。
          *把 bean的实例传递给bean后置处理器的方法：postProcessAfterInitialization
          第四步 获取创建bena实例对象。
          第五步 执行销毁方法。
    *
    *
    * 输出结果：
    * 在初始化之前执行方法
        在初始化之后执行的方法
        第一步 执行无参数构造创建bean实例。
        第二步 调用set方法设置属性值。
        在初始化之前执行方法
        第三步 执行初始化的方法。
        在初始化之后执行的方法
        第四步 获取创建bena实例对象。
    * */
    }



    /*自动注入*/
    @Test
    public void test02(){
        ApplicationContext context =new ClassPathXmlApplicationContext("bean5.xml");

        Emp emp = context.getBean("emp", Emp.class);
        System.out.println(emp);

        /*
        * 输出结果：
        * Emp{dept=Dept{}}
        * */
    }



}
