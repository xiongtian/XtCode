package com.atguigu.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xiongtian
 * @create 2020/10/5-22:09
 */
@ConfigurationProperties(prefix = "atguigu.hello")
public class HelloProperties {

    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
