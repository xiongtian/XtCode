package com.study.spring5.demo4.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author xiongtian
 * @create 2020/9/5-19:27
 */
@Configuration
@ComponentScan(basePackages = {"com.study"})
@EnableAspectJAutoProxy(proxyTargetClass = true) // 开启Aspect生成代理对象
public class ConfigAop {
}
