package com.study.spring5.demo2.CollectionType;

import java.util.*;

public class Stu {
    // 1、数组类型属性
    private String[] course;

    // 2、List集合类型的属性
    private List<String> list;

    // 3、map类型的属性
    private Map<String, String> map;

    // 4、set类型的属性
    private Set<String> set;

    // 5、学生所学的多门课程
    private List<Course> coursesList;

    public void setCoursesList(List<Course> coursesList) {
        this.coursesList = coursesList;
    }

    public void setCourse(String[] course) {
        this.course = course;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void test() {
        System.out.println("Stu{" +
                "course=" + Arrays.toString(course) +
                ", list=" + list +
                ", map=" + map +
                ", set=" + set +
                ", coursesList=" + coursesList +
                '}');
    }
}
