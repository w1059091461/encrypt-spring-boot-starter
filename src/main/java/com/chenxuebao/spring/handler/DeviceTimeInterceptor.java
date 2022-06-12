package com.chenxuebao.spring.handler;


import com.chenxuebao.api.exception.BaseException;
import com.chenxuebao.api.exception.RestCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @program: swis-parent
 * @description: 设备时间handler
 * @author: chenXueBao
 * @create: 2022-03-04 09:47
 **/
@Component
@Slf4j
public class DeviceTimeInterceptor implements HandlerInterceptor {
    private static final Long MONTH_TIME = Long.valueOf(1000*60*60*24*30);
    /**
     * 是否开启设备时间拦截
     **/
    @Value("${ENABLE_DEVICE_TIME_INC}")
    private Boolean ENABLE_DEVICE_TIME_INC;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        if(ENABLE_DEVICE_TIME_INC){
            if (handler instanceof HandlerMethod) {
                DeviceTimeInc deviceTimeInc = ((HandlerMethod) handler).getMethodAnnotation(DeviceTimeInc.class);
                if (null == deviceTimeInc) {
                    deviceTimeInc = ((HandlerMethod) handler).getMethod().getDeclaringClass()
                            .getAnnotation(DeviceTimeInc.class);
                }
                if (null != deviceTimeInc) {
                    final String deviceTimeCipher = request.getHeader("deviceTime");
                    if (StringUtils.isEmpty(deviceTimeCipher)) {
                        log.warn("device Time is null, request uri is" + request.getRequestURI());
                        throw new BaseException(RestCode.SERVICE_UNAVAILABLE);
                    }
                    String deviceTime = null;
                    try {
                        //deviceTime = AESUtil.aesDecode(deviceTimeCipher);
                    }catch (Exception e){
                        log.error("decode time decode error: " + e.getMessage());
                        throw new BaseException(RestCode.SERVICE_UNAVAILABLE);
                    }

                    if (StringUtils.isEmpty(deviceTime)) {
                        log.warn("decode time decode error,cipher is:" + deviceTimeCipher);
                        throw new BaseException(RestCode.SERVICE_UNAVAILABLE);
                    }
                    long originalTime = Long.parseLong(deviceTime);
                    long currentTime = System.currentTimeMillis();
                    if (originalTime + MONTH_TIME * 6 > currentTime) {
                        log.warn("decode run time is > 6 month");
                    } else if (originalTime + MONTH_TIME * 3 > currentTime) {
                        log.warn("decode run time is > 3 month");
                    } else {
                        log.info("decode running...");
                    }
                }

            }
        }
        return true;
    }
}
