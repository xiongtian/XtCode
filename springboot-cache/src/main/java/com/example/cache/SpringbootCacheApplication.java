package com.example.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/*
* 搭建基本环境
*
* 体验快速缓存：
* 1、开启基于注解的缓存  EnableCaching
* 2、标注缓存注解即可
*
*
* 默认使用的是：ConcurrentMapCacheManager==>ConcurrentMapCache:将数据保存在ConcurrentMap里面
* 开发中使用的是缓存中间件：redis、memcached,ehcache
*
*
* 整合redis作为缓存
* 1、安装redis:使用docker
* 2、引入redis的starter
* 3、配置redis
* 4、测试缓存：
*       原理：CacheManager==Cache 缓存组件来实际CRUD数据
*        1)、引入redis的starter，容器中保存的是RedisCacheManager
*        2）、RedisCacheManager帮我们创建 RedisCache 来作为缓存组件： RedisCatch通过操作redis来缓存数据的
*        3）、默认保存数据 k - v 都是Object:利用序列化来保存的；如何保存为json
*                   1、引入redis的starter，cacheManager变为RedisCacheManager；
*                   2、默认创建的redisCacheManager操作redis的时候使用的是RedisTemplate<Object,Object>
*                   3、RedisTemplate<Object,Object>是默认使用jdk的序列化机制
*                   4、自定义CacheManager:
*
*
* */
@MapperScan(value = "com.example.cache.mapper")
@SpringBootApplication
@EnableCaching // 开启缓存的注解
public class SpringbootCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCacheApplication.class, args);
    }

}
