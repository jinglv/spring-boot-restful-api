package com.spring.boot.demo.advance;

import com.spring.boot.demo.common.exception.CustomException;
import com.spring.boot.demo.common.response.ApiResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常使用增强
 *
 * @author jinglv
 * @date 2020/10/18
 */
@Component
@ControllerAdvice(basePackages = "com.spring.boot.demo.controller")
public class ExceptionInterceptor {

    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public ApiResponse<String> exceptionHandler(CustomException e) {
        return ApiResponse.ofException(e);
    }

}
