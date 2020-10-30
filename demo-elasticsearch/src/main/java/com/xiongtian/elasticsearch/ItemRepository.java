package com.xiongtian.elasticsearch;

import com.xiongtian.elasticsearch.pojo.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author xiongtian
 * @create 2020/9/22-0:19
 */
public interface ItemRepository extends ElasticsearchRepository<Item,Long> {

    List<Item> findByTitle(String title);

    List<Item> findByPriceBetween(Double d1,Double d2);
}
