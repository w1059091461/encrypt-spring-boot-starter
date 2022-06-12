package com.chenxuebao.spring.aspects;

/**
 * @Author: chenXueBao
 * @Date: 2022/3/25 01:14
 * @Description: 方法耗时统计
 * @version: V1.0
 */
public class MethodProfiler {
    private static final ThreadLocal<Long> TIME_THREAD = new ThreadLocal<Long>() {
        protected Long init(){
            return System.currentTimeMillis();
        }
    };

    public static final void begin(){
        TIME_THREAD.set(System.currentTimeMillis());
    }

    public static final Long end(){
        return System.currentTimeMillis() - TIME_THREAD.get();
    }
}
