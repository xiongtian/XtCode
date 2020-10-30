package com.example.elasticsearch.repository;

import com.example.elasticsearch.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author xiongtian
 * @create 2020/10/19-23:09
 */
public interface BookRepository extends ElasticsearchRepository<Book,Integer> {
}
