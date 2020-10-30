package com.example.springbootd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// 批量扫描所有的mapper
@MapperScan(value = "com.example.springbootd.mapper" )
@SpringBootApplication
public class SpringBootDataMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataMybatisApplication.class, args);
    }

}
