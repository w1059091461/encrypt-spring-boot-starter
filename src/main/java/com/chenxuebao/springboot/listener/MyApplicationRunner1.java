package com.chenxuebao.springboot.listener;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @program: spring-util
 * @description: ApplicationRunner 和 CommandLineRunner 功能一致，用法也基本一致，唯一的区别主要体现在对参数的处理上，ApplicationRunner
 * 可以接收更多类型的参数（ApplicationRunner 除了可以接收 CommandLineRunner 的参数之外，还可以接收 key/value 形式的参数）。
 * @author: 陈学宝
 * @create: 2022-06-26 22:45
 **/
@Component
@Order(98)
public class MyApplicationRunner1 implements ApplicationRunner {
    /**
     * @Description: args.getNonOptionArgs();可以用来获取命令行中的无key参数（和CommandLineRunner一样）。
     * args.getOptionNames();可以用来获取所有key/value形式的参数的key。
     * args.getOptionValues(key));可以根据key获取key/value 形式的参数的value。
     * args.getSourceArgs(); 则表示获取命令行中的所有参数。
     * @Author:陈学宝
     * @Date: 2022/6/26 22:46
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> nonOptionArgs = args.getNonOptionArgs();
        System.out.println("MyApplicationRunner1>>>"+nonOptionArgs);
        Set<String> optionNames = args.getOptionNames();
        for (String key : optionNames) {
            System.out.println("MyApplicationRunner1>>>"+key + ":" + args.getOptionValues(key));
        }
        String[] sourceArgs = args.getSourceArgs();
        System.out.println("MyApplicationRunner1>>>"+ Arrays.toString(sourceArgs));
    }
}
