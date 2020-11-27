package com.spring.boot.demo.common.response;

import lombok.Getter;

/**
 * @author jingLv
 * @date 2020-04-21 4:52 PM
 */
@Getter
public enum ApiCodeEnum {

    /**
     * 接口返回正确
     */
    SUCCESS("00000", "成功"),

    /**
     * 接口返回错误
     */
    FAIL("00001", "失败"),

    /**
     * 接口返回参数不正确
     */
    PARAM_ERROR("1000", "参数不正确");

    /**
     * 业务状态码
     */
    private final String code;

    /**
     * 业务状态信息
     */
    private final String message;

    /**
     * 常量枚举，不能外部实例化
     *
     * @param code    响应编码
     * @param message 响应描述
     */
    ApiCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
