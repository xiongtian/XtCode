package com.study.spring5.demo5.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author xiongtian
 * @create 2020/9/5-23:49
 */
@Repository(value = "myUserDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
    * 多钱的方法
    * Marry多一百块钱
    * */

    public void addMoney() {
        String sql="update t_account set money=money + ? where username = ?";
        jdbcTemplate.update(sql,100,"Marry");
    }

    /* 少钱的方法
    * lucy转账100给lucy
    * */
    public void reduceMoney() {

        String sql="update t_account set money=money - ? where username = ?";
        jdbcTemplate.update(sql,100,"Lucy");
    }





}
