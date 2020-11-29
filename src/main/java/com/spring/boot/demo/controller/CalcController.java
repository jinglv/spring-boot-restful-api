package com.spring.boot.demo.controller;

import com.spring.boot.demo.annotation.JwtSupport;
import com.spring.boot.demo.common.response.ApiResponse;
import com.spring.boot.demo.service.CalcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author jingLv
 * @date 2020/10/16
 */
@Validated
@JwtSupport
@RestController
@Slf4j
@RequestMapping("/v1/calc")
@Api(tags = "计算相关接口", value = "CalcController", protocols = "JSON")
public class CalcController {

    private final CalcService calcService;

    public CalcController(CalcService calcService) {
        this.calcService = calcService;
    }

    @ApiOperation(value = "计算算法加法",
            notes = "计算算法加法",
            produces = "application/json, application/xml",
            consumes = "application/json, application/xml",
            httpMethod = "GET",
            response = List.class)
    @GetMapping(value = "add")
    public ApiResponse<Object> add(@NotNull(message = "number1不能为空") @RequestParam Integer number1, @NotNull(message = "number2不能为空") @RequestParam Integer number2) {
        return ApiResponse.ofSuccess(this.calcService.add(number1, number2));
    }

    @ApiOperation(value = "计算算法减法",
            notes = "计算算法加法",
            produces = "application/json, application/xml",
            consumes = "application/json, application/xml",
            httpMethod = "GET",
            response = List.class)
    @GetMapping(value = "sub")
    public ApiResponse<Object> sub(@NotNull(message = "number1不能为空") @RequestParam Integer number1, @NotNull(message = "number2不能为空") @RequestParam Integer number2) {
        return ApiResponse.ofSuccess(this.calcService.sub(number1, number2));
    }

    @ApiOperation(value = "计算算法乘法",
            notes = "计算算法加法",
            produces = "application/json, application/xml",
            consumes = "application/json, application/xml",
            httpMethod = "GET",
            response = List.class)
    @GetMapping(value = "mul")
    public ApiResponse<Object> mul(@NotNull(message = "number1不能为空") @RequestParam Integer number1, @NotNull(message = "number2不能为空") @RequestParam Integer number2) {
        return ApiResponse.ofSuccess(this.calcService.mul(number1, number2));
    }

    @ApiOperation(value = "计算算法除法",
            notes = "计算算法加法",
            produces = "application/json, application/xml",
            consumes = "application/json, application/xml",
            httpMethod = "GET",
            response = List.class)
    @GetMapping(value = "div")
    public ApiResponse<Object> div(@NotNull(message = "number1不能为空") @RequestParam Integer number1, @NotNull(message = "number2不能为空") @RequestParam Integer number2) {
        return ApiResponse.ofSuccess(this.calcService.div(number1, number2));
    }
}
