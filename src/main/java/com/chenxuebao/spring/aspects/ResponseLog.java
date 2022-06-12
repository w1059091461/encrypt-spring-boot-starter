package com.chenxuebao.spring.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口回复日志注解
 * @author David zheng
 */
@Target(value = { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseLog {
    boolean value() default true;// 默认打日志
}