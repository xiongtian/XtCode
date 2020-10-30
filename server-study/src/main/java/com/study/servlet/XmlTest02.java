package com.study.servlet;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class XmlTest02 {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //SAX解析
        //1、获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //2、从解析工厂获取解析器
        SAXParser parse = factory.newSAXParser();
        //3、编写处理器
        //4、加载文档 Document 注册处理器
        WebHandler handler = new WebHandler();
        //5、解析
        parse.parse(Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("web.xml")
                , handler);

        System.out.println("=========================华丽的分割线===============================");
        // 获取数据
        List<Entity> entities = handler.getEntities();
        List<Mapping> mappings =handler.getMappings();
        WebContext context=new WebContext(entities,mappings);
        // 假设你输入了 /login
        String name = context.getClz("/login");
        Class<?> clz = Class.forName(name);
        Servlet servlet = (Servlet) clz.getConstructor().newInstance();
        servlet.service();

        entities.forEach(System.out::println);
        mappings.forEach(System.out::println);
    }
}

class WebHandler extends DefaultHandler {

    private List<Entity> entities;
    private List<Mapping> mappings;
    private Entity entity;
    private Mapping mapping;

    private String tag; // 存储操作的标签

    private boolean isMapping = false;

    @Override
    public void startDocument() throws SAXException {
        entities = new ArrayList<>();
        mappings = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (null != qName) {
            tag = qName;//存储标签名
            if (tag.equals("servlet")) {
                entity = new Entity();
                isMapping=false;
            }else if (tag.equals("servlet-mapping")){
                mapping = new Mapping();
                isMapping=true;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String contents = new String(ch, start, length);
        if (null != tag) { //处理空值的问题
            if (isMapping){ // 操作servlet-mapping
                if (contents.length() > 0) {
                    if (tag.equals("servlet-name")) {
                        mapping.setName(contents);
                    } else if (tag.equals("url-pattern")) {
                        mapping.addPattern(contents);
                    }
                }
            }else { // 操作servlet
                if (contents.length() > 0) {
                    if (tag.equals("servlet-name")) {
                        entity.setName(contents);
                    } else if (tag.equals("servlet-class")) {
                        entity.setClz(contents);
                    }
                }
            }

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println(qName + "-->解析结束！");
        if (null != qName) { //处理空值的问题

            if (qName.equals("servlet")) {
                entities.add(entity);
            }else if (qName.equals("servlet-mapping")){
                mappings.add(mapping);
            }
        }
        tag = null; // 丢弃tag
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Mapping> getMappings() {
        return mappings;
    }
}
