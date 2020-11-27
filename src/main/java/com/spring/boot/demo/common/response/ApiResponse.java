package com.spring.boot.demo.common.response;

import cn.hutool.core.util.StrUtil;
import com.spring.boot.demo.common.exception.CustomException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * @author jingLv
 * @date 2020/10/09
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(description = "API统一返回体", value = "JSON")
public class ApiResponse<T> {

    /**
     * 状态码.
     */
    @ApiModelProperty(value = "响应编码，成功返回：00000", example = "00000", required = true)
    private String code;

    /**
     * 响应描述.
     */
    @ApiModelProperty(value = "响应描述", example = "ok", required = true)
    private String message;

    /**
     * 返回数据.
     */
    @ApiModelProperty(value = "返回数据")
    private T data;

    /**
     * 全参构造函数
     *
     * @param code    状态码
     * @param message 返回内容
     * @param data    返回数据
     */
    private ApiResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 构造一个自定义的API返回
     *
     * @param code    状态码
     * @param message 返回内容
     * @return ApiResponse
     */
    public static <T> ApiResponse<T> of(String code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    /**
     * 构造一个自定义的API返回
     *
     * @param code    状态码
     * @param message 返回内容
     * @param data    返回数据
     * @return ApiResponse
     */
    public static <T> ApiResponse<T> of(String code, String message, T data) {
        return new ApiResponse<>(code, message, data);
    }

    /**
     * 构造一个成功且带数据的API返回
     *
     * @param data 返回数据
     * @return ApiResponse
     */
    public static <T> ApiResponse<T> ofSuccess(T data) {
        return ofErrorCode(ApiCodeEnum.SUCCESS, data);
    }

    /**
     * 构造一个成功且带数据的API返回
     *
     * @return ApiResponse
     */
    public static <T> ApiResponse<T> ofSuccess() {
        return ofErrorCode(ApiCodeEnum.SUCCESS, null);
    }

    /**
     * 构造一个成功且自定义消息的API返回
     *
     * @param message 返回内容
     * @return ApiResponse
     */
    public static <T> ApiResponse<T> ofMessage(String message) {
        return of(ApiCodeEnum.SUCCESS.getCode(), message, null);
    }


    /**
     * 构造一个有状态的API返回
     *
     * @param apiCodeEnum 状态
     * @return ApiResponse
     */
    public static <T> ApiResponse<T> ofErrorCode(ApiCodeEnum apiCodeEnum) {
        return ofErrorCode(apiCodeEnum, null);
    }

    /**
     * 构造一个有状态且带数据的API返回
     *
     * @param apiCodeEnum 状态
     * @param data        返回数据
     * @return ApiResponse
     */
    public static <T> ApiResponse<T> ofErrorCode(ApiCodeEnum apiCodeEnum, T data) {
        return of(apiCodeEnum.getCode(), apiCodeEnum.getMessage(), data);
    }

    /**
     * 构造一个异常且带数据的API返回
     *
     * @param e    异常
     * @param data 返回数据
     * @param <E>  {@link CustomException} 的子类
     * @return ApiResponse
     */
    public static <E extends CustomException, T> ApiResponse<T> ofException(E e, T data) {
        return of(StrUtil.isNotBlank(e.getCode()) ? e.getCode() : ApiCodeEnum.FAIL.getCode(), e.getMessage(), data);
    }

    /**
     * 构造一个异常且带数据的API返回
     *
     * @param e   异常
     * @param <E> {@link CustomException} 的子类
     * @return ApiResponse
     */
    public static <E extends CustomException, T> ApiResponse<T> ofException(E e) {
        return ofException(e, null);
    }

    /**
     * 判断当前实例是否是成功的状态
     *
     * @return 如果成功，则返回true，否则返回false
     */
    public boolean isSuccess() {
        return Objects.equals(this.code, ApiCodeEnum.SUCCESS.getCode());
    }
}
