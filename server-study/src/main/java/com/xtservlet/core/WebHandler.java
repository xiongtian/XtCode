package com.xtservlet.core;

import com.study.servlet.Entity;
import com.study.servlet.Mapping;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 处理器
 * @author xiongtian
 * @create 2020/9/26-23:29
 */
public class WebHandler extends DefaultHandler {

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
