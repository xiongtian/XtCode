package com.example.amqp;

import com.example.amqp.bean.Book;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringgbootAmqpApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;
    /**
     *
     * 1、单播：（点对点）
     *
     */
    @Test
    void contextLoads() {

        // message需要自己构造一个；可以定制消息体的内容和消息头
        // rabbitTemplate.send(exchange,routKey,message);

        // object默认当成消息体，只需要传入要发送的对象，自动序列化发送给RabbitMQ
        // rabbitTemplate.convertAndSend(exchange,routKey,object);
        Map<String,Object> map =new HashMap<>();
        map.put("msg","这是第一个消息");
        map.put("data", Arrays.asList("helloWorld",123,true));
        // 对象被默认序列化之后发送出去
        //rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",map);
       // rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",new Book("西游记","吴承恩"));
       // rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",new Book("西游记","吴承恩"));
        rabbitTemplate.convertAndSend("exchange.direct","atguigu",new Book("西游记","吴承恩"));

    }


    // 接收数据，如何将数据自动的转为json发送出去
    @Test
    public void  recieve(){

        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    /*
    * 广播
    * */
    @Test
    public void sendMsg(){
        rabbitTemplate.convertAndSend("exchange.fanout"," ",new Book("三国演义","罗贯中"));
    }


    @Test
    public void creatExchange(){
        // 创建Exchange
      /*  amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
        System.out.println("创建完成！");*/

        // 创建队列
        //amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));

        // 创建绑定规则
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE,"amqpadmin.exchange","amqp.haha",null));
    }
}
