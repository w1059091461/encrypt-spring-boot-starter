package com.chenxuebao.spring.handler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: swis-parent
 * @description: 设备时间拦截
 * @author: chenXueBao
 * @create: 2022-03-04 09:47
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DeviceTimeInc {
}
