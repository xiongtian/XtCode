package com.example.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
* 自动配置：
*       1、RabbitAutoConfiguration
*       2、有自动配置的连接工厂: ConnectionFactor
*       3、RabbitProperties 封装了RabbitMQ的所有配置
*       4、RabbitTemplate ： 给RabbitMQ发送和接收消息
*       5、AmqpAdmin : RabbitMQ的系统功能管理组件
*               AmqpAdmin：创建和删除 Queue,Exchange,Binding
*       6、@EnableRabbit + @RabbitListener 监听消息队列的内容
* */
@EnableRabbit  // 开启基于注解的RabbitMQ
@SpringBootApplication
public class SpringgbootAmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringgbootAmqpApplication.class, args);
    }

}
