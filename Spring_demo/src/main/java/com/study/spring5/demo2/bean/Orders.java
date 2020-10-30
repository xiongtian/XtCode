package com.study.spring5.demo2.bean;

/**
 * @author xiongtian
 * @create 2020/9/3-22:33
 */
public class Orders {
    private String oname;

    // 无参数的构造
    public Orders() {
        System.out.println("第一步 执行无参数构造创建bean实例。");
    }

    public void setOname(String oname) {
        this.oname = oname;
        System.out.println("第二步 调用set方法设置属性值。" );
    }

    // 创建执行的初始化的方法
    public void initMethod() {

        System.out.println("第三步 执行初始化的方法。");
    }

    // 容器关闭时执行的方法
    public void destroyMethod(){
        System.out.println("第五步 执行销毁方法。");
    }
}
