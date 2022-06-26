package com.chenxuebao.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: spring-util
 * @description:
 * @author: 陈学宝
 * @create: 2022-06-26 21:54
 **/
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    /**
     * @Description: 自定义静态资源映射策略
     * 另一种方式：
     * spring.resources.static-locations=classpath:/aaa
     * spring.mvc.static-path-pattern=/**
     * @Author:陈学宝
     * @Date: 2022/6/26 21:56
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/aaa/");
    }
}
