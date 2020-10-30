package com.study.spring5.demo2.CollectionType;

import java.util.List;

public class Book {
    private List<String> list;

    public void setList(List<String> list) {
        this.list = list;
    }

    public void  test() {
        System.out.println("Book{" + "list=" + list + '}');
    }
}
