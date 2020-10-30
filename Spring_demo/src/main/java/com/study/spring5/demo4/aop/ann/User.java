package com.study.spring5.demo4.aop.ann;

import org.springframework.stereotype.Component;

/**
 * @author xiongtian
 * @create 2020/9/5-17:53
 */
@Component
public class User {
    public void add() {
        // 手动添加异常
        //int i=10/0;
        System.out.println("add.........");
    }
}
