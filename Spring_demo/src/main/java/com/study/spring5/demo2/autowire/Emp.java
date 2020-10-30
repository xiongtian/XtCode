package com.study.spring5.demo2.autowire;

/**
 * @author xiongtian
 * @create 2020/9/3-23:10
 */
public class Emp {
    private Dept dept;

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "dept=" + dept +
                '}';
    }


    public void test() {
        System.out.println(dept);
    }
}
