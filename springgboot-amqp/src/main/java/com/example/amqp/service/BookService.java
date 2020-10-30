package com.example.amqp.service;

import com.example.amqp.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @RabbitListener(queues = {"atguigu.news"})
    public void recieve(Book book){
        System.out.println("收到消息："+book);
    }

    @RabbitListener(queues = "atguigu")
    public void recieve02(Message messgae) {
        messgae.getBody();
        System.out.println();
        System.out.println(messgae.getMessageProperties());
    }
}
