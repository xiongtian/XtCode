package com.study.spring5.demo4.aop.ann;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author xiongtian
 * @create 2020/9/5-18:48
 */
@Component
@Aspect
@Order(value = 3)
public class PersonProxy {

    // 后置通知（有异常就不执行）或者返回通知
    /*方法返回值之后执行*/
    @Before(value = "execution(* com.study.spring5.demo4.aop.ann.User.add(..))")
    public void Before() {
        System.out.println("Person Before");
    }
}
