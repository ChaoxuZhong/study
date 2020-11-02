package com.chaoxuzhong.study.globalException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Map<String, Object> handleException(Exception exception) {
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("errorCode", "500");
        stringObjectMap.put("errorMsg", exception.getMessage());
        return stringObjectMap;
    }
}
