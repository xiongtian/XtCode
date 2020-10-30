package com.study.prepare;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlTest02 {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        //SAX解析
        //1、获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //2、从解析工厂获取解析器
        SAXParser parse = factory.newSAXParser();
        //3、编写处理器
        //4、加载文档 Document 注册处理器
        PersonHandler handler = new PersonHandler();
        //5、解析
        parse.parse(Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("p.xml")
                , handler);

        System.out.println("=========================华丽的分割线===============================");
        // 获取数据
        List<Person> persons = handler.getPersons();
        persons.forEach(System.out::println);
    }
}

class PersonHandler extends DefaultHandler {

    private List<Person> persons;
    private Person person;
    private String tag; // 存储操作的标签

    @Override
    public void startDocument() throws SAXException {
        persons = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (null != qName) {
            tag = qName;//存储标签名
            if (tag.equals("person")) {
                person = new Person();
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String contents = new String(ch, start, length);
        if (null != tag) { //处理空值的问题
            if (contents.length() > 0) {
                if (tag.equals("name")) {
                    person.setName(contents);
                } else if (tag.equals("age")) {
                    person.setAge(Integer.parseInt(contents));
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println(qName + "-->解析结束！");
        if (null != qName) { //处理空值的问题

            if (qName.equals("person")) {
                persons.add(person);
            }
        }
        tag = null; // 丢弃tag
    }

    @Override
    public void endDocument() throws SAXException {

    }

    public List<Person> getPersons() {
        return persons;
    }
}