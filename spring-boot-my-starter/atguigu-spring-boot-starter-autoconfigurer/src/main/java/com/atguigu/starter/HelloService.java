package com.atguigu.starter;

/**
 * @author xiongtian
 * @create 2020/10/5-22:08
 */
public class HelloService {

    HelloProperties helloProperties;

    public String sayHelloAtguigu(String name){
        return helloProperties.getPrefix()+"-"+name+":"+helloProperties.getSuffix()+"!";
    }

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }
}
