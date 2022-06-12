package com.chenxuebao.util.encrypt.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @Description:
 * @Author:陈学宝
 * @Date: 2022/6/12 18:14
 */
@ConfigurationProperties(prefix = "spring.encrypt")
public class EncryptProperties {
    private final static String DEFAULT_KEY = "www.itboyhub.com";
    private String key = DEFAULT_KEY;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
