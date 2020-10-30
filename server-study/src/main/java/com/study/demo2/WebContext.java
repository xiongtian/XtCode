package com.study.demo2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebContext {
    private List<Entity> entities = null;
    private List<Mapping> mappings = null;

    /*
    key ---> servlet-name
    value ---> servlet-class
    * */
    private Map<String,String> entityMap = new HashMap<>();
    /*
    key ---> url-pattern
    value ---> servlet-name
    */
    private Map<String,String> mappingMap = new HashMap<>();

    public WebContext(List<Entity> entities, List<Mapping> mappings) {
        this.entities = entities;
        this.mappings = mappings;

        // 将entity的List转换为map
        for (Entity entity:entities) {
            entityMap.put(entity.getName(),entity.getClz());
        }

        // 将mapping的List转换为map
        for (Mapping mapping:mappings) {
            for (String parttern: mapping.getPatterns()) {
                mappingMap.put(parttern,mapping.getName());
            }
        }
    }

    /**
     * 通过URL找到了对应的class
     * @param pattern
     * @return
     */
    public String getClz(String pattern) {

        return entityMap.get(mappingMap.get(pattern));
    }
}
