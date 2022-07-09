package com.chenxuebao.springboot.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @program: spring-util
 * @description: Spring Boot 中针对系统启动任务提供了两种解决方案，分别是 CommandLineRunner 和 ApplicationRunner.
 * @author: 陈学宝
 * @create: 2022-06-26 22:43
 **/
@Component
@Order(100)
public class MyCommandLineRunner1 implements CommandLineRunner {
    /**
     * @Description:
     * @param args: run 方法的参数，来自于项目的启动参数，即项目入口类中，main方法的参数会被传到这里。
     * @Author:陈学宝
     * @Date: 2022/6/26 22:44
     */
    @Override
    public void run(String... args) throws Exception {
    }
}
