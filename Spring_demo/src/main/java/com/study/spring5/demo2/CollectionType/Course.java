package com.study.spring5.demo2.CollectionType;

// 课程类
public class Course {

    // 课程名称
    private String cname;

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cname='" + cname + '\'' +
                '}';
    }
}
