package com.spring.boot.demo.advance;

import com.spring.boot.demo.common.exception.CustomException;
import com.spring.boot.demo.common.response.ApiCodeEnum;
import com.spring.boot.demo.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Component
@ControllerAdvice(basePackages = "com.spring.boot.demo.controller")
public class ExceptionInterceptor {

    /**
     * 拦截业务类异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public ApiResponse<String> exceptionHandler(CustomException e) {
        log.error("捕捉业务类异常:" + e);
        return ApiResponse.ofErrorCode(ApiCodeEnum.FAIL, e.getMessage());
    }

    /**
     * 拦截运行类异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<String> runtimeException(RuntimeException e) {
        log.error("捕捉运行时异常：" + e);
        return ApiResponse.ofErrorCode(ApiCodeEnum.FAIL, e.getMessage());
    }

    /**
     * 拦截类Throwable异常
     *
     * @param th
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public ApiResponse<String> throwableHandle(Throwable th) {
        log.error("捕捉Throwable异常：", th);
        return ApiResponse.ofErrorCode(ApiCodeEnum.FAIL, th.getMessage());
    }

}
