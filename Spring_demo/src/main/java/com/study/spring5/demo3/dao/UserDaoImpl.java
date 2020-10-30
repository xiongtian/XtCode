package com.study.spring5.demo3.dao;

import org.springframework.stereotype.Repository;

@Repository(value = "userDaoImpl1")
public class UserDaoImpl implements UserDao {
    public void add() {

        System.out.println("dao add");
    }
}
