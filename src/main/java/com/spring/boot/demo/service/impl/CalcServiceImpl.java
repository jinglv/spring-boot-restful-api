package com.spring.boot.demo.service.impl;

import cn.hutool.core.lang.Assert;
import com.spring.boot.demo.service.CalcService;
import org.springframework.stereotype.Service;

/**
 * @author jingLv
 * @date 2020/10/16
 */
@Service
public class CalcServiceImpl implements CalcService {

    private static final String ERROR_MESSAGE = "the argument {} is required, it must be not null.";
    private static final String PARAMETER_A = "a";
    private static final String PARAMETER_B = "b";

    @Override
    public Integer add(Integer a, Integer b) {
        Assert.notNull(a, ERROR_MESSAGE, PARAMETER_A);
        Assert.notNull(b, ERROR_MESSAGE, PARAMETER_B);
        return a + b;
    }

    @Override
    public Integer sub(Integer a, Integer b) {
        Assert.notNull(a, ERROR_MESSAGE, PARAMETER_A);
        Assert.notNull(b, ERROR_MESSAGE, PARAMETER_B);
        return a - b;
    }

    @Override
    public Integer mul(Integer a, Integer b) {
        Assert.notNull(a, ERROR_MESSAGE, PARAMETER_A);
        Assert.notNull(b, ERROR_MESSAGE, PARAMETER_B);
        return a * b;
    }

    @Override
    public Integer div(Integer a, Integer b) {
        Assert.notNull(a, ERROR_MESSAGE, PARAMETER_A);
        Assert.notNull(b, ERROR_MESSAGE, PARAMETER_B);
        if (b == 0) {
            throw new ArithmeticException("被除数不可为0");
        }
        return a / b;
    }
}
