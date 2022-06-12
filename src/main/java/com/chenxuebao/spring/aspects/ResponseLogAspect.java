package com.chenxuebao.spring.aspects;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class ResponseLogAspect extends BaseAspectSupport{

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 120s
     **/
    private static final long WARN_TIME = 1000*120;


    @Around(value = "@annotation(com.chenxuebao.spring.aspects.ResponseLog)")
    public Object doAfter(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodProfiler.begin();
        Object result = proceedingJoinPoint.proceed();
        Long end = MethodProfiler.end();
        Method targetMethod = resolveMethod(proceedingJoinPoint);
        // 请求的方法名
        String methodName = targetMethod.getName();
        // 请求的类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        // 请求参数
        Object[] args = proceedingJoinPoint.getArgs();
        if(WARN_TIME > end){
            log.info("方法耗时：{}s，类名和方法名: {}|{},request data: {}",String.format("%.2f", end/1000.0),className,methodName,JSON.toJSONString(args));
        }else {
            log.warn("方法耗时：{}ms，类名和方法名: {}|{}",end,className,methodName);
        }
        log.info("接口回复内容: {}", JSON.toJSONString(result));
        return result;
    }
}
