package com.study.demo02.bean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;
/*
* 将配置文件的每一个属性的值，映射到这个组件中
*@ConfigurationProperties:告诉SpringBoot将本类中的所有属性和配置文件进行绑定；
*prefix（前缀）:配置文件中那个下面的所有属性进行一一映射
* 只有这个组件是容器中的组件，才能在容器提供的功能
* */
@Component
//@PropertySource(value = {"classpath:person.properties"})//读取指定的配置文件
@ConfigurationProperties(prefix = "person")//方式一：默认从全局配置文件中获取值
public class Person {


    //@Value("${person.lastName}")
    //lastName必须是邮箱格式的
   // @Email
   //    @Value("${person.last-name}")
    private String LastName;

    //@Value("#{11*2}")
    private Integer age;

   // @Value("true")
    private boolean boss;

    private Date birthday;

    private Map<String, Object> maps;

    private List<Object> list;

    private Dog dog;

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isBoss() {
        return boss;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "LastName='" + LastName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birthday=" + birthday +
                ", maps=" + maps +
                ", list=" + list +
                ", dog=" + dog +
                '}';
    }
}
