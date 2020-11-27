package com.spring.boot.demo.common.exception;

import com.spring.boot.demo.common.response.ApiCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 统一异常处理
 *
 * @author jingLv
 * @date 2020/10/09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomException extends RuntimeException {
    private final String code;

    public CustomException() {
        super();
        this.code = ApiCodeEnum.FAIL.getCode();
    }

    public CustomException(String message) {
        super(message);
        this.code = ApiCodeEnum.FAIL.getCode();
    }

    public CustomException(Throwable cause) {
        super(cause);
        this.code = ApiCodeEnum.FAIL.getCode();
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
        this.code = ApiCodeEnum.FAIL.getCode();
    }

    public CustomException(String code, String message) {
        this(code, message, null);
    }

    public CustomException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
