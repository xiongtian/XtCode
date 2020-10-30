package com.study.spring5.demo4.aop.ann;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author xiongtian
 * @create 2020/9/5-17:56
 * 被增强的类
 */
@Component
@Aspect  // 生成代理对象
@Order(value = 1)
public class UserProxy {


    // 相同切入点抽取
    @Pointcut(value ="execution(* com.study.spring5.demo4.aop.ann.User.add(..))" )
    private void pointdemo(){ }

    // 前置通知
    // @Before注解表示作为前置通知
    @Before(value = "pointdemo()") //配置通知
    public void before() {
        System.out.println("before....");
    }

    // 最终通知（有没有异常都执行哦）
    /*方法之后执行*/
    @After(value="pointdemo()")
    public void after() {
        System.out.println("after");
    }

    // 后置通知（有异常就不执行）或者返回通知
    /*方法返回值之后执行*/
    @AfterReturning(value="pointdemo()")
    public void AfterReturning() {
        System.out.println("AfterReturning");
    }

    /*异常通知*/
    @AfterThrowing(value="pointdemo()")
    public void AfterThrowing() {
        System.out.println("AfterThrowing");
    }

    @Around("execution(* com.study.spring5.demo4.aop.ann.User.add(..))")
    public void Around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Around：环绕之前：");

        // 被增强的方法执行
        proceedingJoinPoint.proceed();

        System.out.println("Around：环绕之后：");

    }

}
