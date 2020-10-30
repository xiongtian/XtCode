package com.example.elasticsearch;

import com.example.elasticsearch.bean.Book;
import com.example.elasticsearch.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootElasticsearchApplicationTests {


    @Autowired
    BookRepository bookRepository;

    @Test
    void contextLoads() {

        Book book = new Book();
        bookRepository.index(book);
    }

}
