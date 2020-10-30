package com.study.prepare;

import java.lang.reflect.InvocationTargetException;


/*
* 反射的三种方式
* */
public class ReflectTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 第一种: 对象.getClass();
        Class aClass = new Iphone().getClass();
        // 第二种: 类名.class
        Class c=Iphone.class;
        // 第三种: Class.forName("路径名")
        Class<?> clazz = Class.forName("com.study.prepare.Iphone");
        System.out.println(clazz);
        Object o = clazz.getConstructor().newInstance();
        System.out.println(o);

    }
}
class Iphone{
    public Iphone() {

    }
}