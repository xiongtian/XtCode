package com.study.spring5.demo5.entity;

/**
 * @author xiongtian
 * @create 2020/9/5-23:46
 */
public class Account {
    private String id;
    private String username;
    private Integer money;

    public Account() {
    }

    public Account(String id, String username, Integer money) {
        this.id = id;
        this.username = username;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
