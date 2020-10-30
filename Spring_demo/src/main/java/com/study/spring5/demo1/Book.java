package com.study.spring5.demo1;

public class Book {

    private String name;
    private String author;

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public Book() {
    }

    public Book(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        // get注入
        Book book  = new Book();
        book.setName("haha ");

        // 有参数的构造方法注入
        Book book1 = new Book("haha");
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
