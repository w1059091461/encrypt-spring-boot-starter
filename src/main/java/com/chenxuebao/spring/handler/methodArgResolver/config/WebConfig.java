package com.chenxuebao.spring.handler.methodArgResolver.config;

import com.chenxuebao.spring.handler.methodArgResolver.CurrentUserNameHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @program: spring-util
 * @description:
 * @author: 陈学宝
 * @create: 2022-06-13 23:54
 *
 * 例：
 * @RestController
 * public class HelloController {
 *     @GetMapping("/hello")
 *     public String hello(@CurrentUserName String name) {
 *         return "hello "+name;
 *     }
 * }
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CurrentUserNameHandlerMethodArgumentResolver());
    }
}

