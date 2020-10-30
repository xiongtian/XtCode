package com.study.spring5.demo2.factoryBean;

import com.study.spring5.demo2.CollectionType.Course;
import org.springframework.beans.factory.FactoryBean;

public class MyBean implements FactoryBean<Course> {

    /*返回的实例
     * 定义返回的bean
     * */
    @Override
    public Course getObject() throws Exception {
        Course course =new Course();
        course.setCname("abd");
        return course;
    }

    // 返回的类型
    @Override
    public Class<?> getObjectType() {
        return null;
    }

    // 是否是单例的
    @Override
    public boolean isSingleton() {
        return false;
    }
}
