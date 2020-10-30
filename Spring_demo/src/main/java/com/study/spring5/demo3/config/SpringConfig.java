package com.study.spring5.demo3.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
* 全注解开发：
* 第一步：创建配置类，代替配置文件
* 第二步：编写测试类
*
*
* */
@Configuration //作为配置类，替代xml配置文件
@ComponentScan(basePackages = {"com.study"})
public class SpringConfig {


}
