package com.study.demo2;
/*
* <servlet>
        <servlet-name>reg</servlet-name>
        <servlet-class>com.sxt.server.basic.servlet.RegisterServlet</servlet-class>
    </servlet>
*
* */
public class Entity {

    private String name;
    private String clz;

    public Entity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClz() {
        return clz;
    }

    public void setClz(String clz) {
        this.clz = clz;
    }



    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", clz='" + clz + '\'' +
                '}';
    }
}
