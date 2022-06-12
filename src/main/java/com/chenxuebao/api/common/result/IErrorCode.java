package com.chenxuebao.api.common.result;


/**
 * @Author: 陈学宝
 * @Description: 常用API返回对象接口
 * @Date: 1:05 2022/4/24
 * @Param:
 * @return:
 **/
public interface IErrorCode {
    /**
     * 返回码
     */
    long getCode();

    /**
     * 返回信息
     */
    String getMessage();
}
