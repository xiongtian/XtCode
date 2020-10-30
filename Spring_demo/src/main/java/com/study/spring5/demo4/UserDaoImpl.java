package com.study.spring5.demo4;

/**
 * @author xiongtian
 * @create 2020/9/5-16:04
 */
public class UserDaoImpl implements UserDao {
    public int add(int a, int b) {
        return a+b;
    }

    public String update(String id) {
        return id;
    }
}
