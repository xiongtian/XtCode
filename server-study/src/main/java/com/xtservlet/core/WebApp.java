package com.xtservlet.core;


import com.study.servlet.Entity;
import com.study.servlet.Mapping;
import com.study.servlet.WebContext;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;


/**
 * @author xiongtian
 * @create 2020/9/26-23:27
 */
public class WebApp {
    private static WebContext webContext;

    static {
        try {
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

            // 获取数据
            List<Entity> entities = handler.getEntities();
            List<Mapping> mappings = handler.getMappings();
            webContext = new WebContext(entities, mappings);
        } catch (Exception e) {
            System.out.println("解析配置文件错误");
        }
    }

    /**
     * 通过url获取配置文件对应的servlet
     * @param url
     * @return
     */
    public static Servlet getServletFromUrl(String url) {

        // 假设你输入了 /login
        String name = webContext.getClz("/"+url);
        Class<?> clz = null;
        try {
            clz = Class.forName(name);
            Servlet servlet = (Servlet) clz.getConstructor().newInstance();
            return servlet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        entities.forEach(System.out::println);
        mappings.forEach(System.out::println);*/
        return null;
    }
}
