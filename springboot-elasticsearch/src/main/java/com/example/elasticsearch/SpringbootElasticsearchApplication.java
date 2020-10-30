package com.example.elasticsearch;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/*
* SpringBoot默认支持两种技术来个ES交互
*   1、Jest(默认不生效)
*       需要导入jest的工具包（io.searchbox.client.JestClient）
*   2、SpringData ElasticSearch【Es版本有可能不合适】
*                       如果版本不适配：
*                            1)、升级SpringBoot版本
*                            2)、安装对应版本的ES
*           1)、Client 节点信息 ClusterNodes： ClusterName
*           2）、ElasticsearchTemplate:操作es
*           3）、编写一个ElasticsearchRepository的子接口来操作ES
* 两种方法：
* 1)、编写一个 ElasticsearchRepository子接口
* */
@SpringBootApplication
public class SpringbootElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootElasticsearchApplication.class, args);
    }


}
