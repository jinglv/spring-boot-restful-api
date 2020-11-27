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
    @Override
    public Integer add(Integer a, Integer b) {
        Assert.notNull(a, "the argument a is required, it must be not null.");
        Assert.notNull(b, "the argument b is required, it must be not null.");
        return a + b;
    }

    @Override
    public Integer sub(Integer a, Integer b) {
        Assert.notNull(a, "the argument a is required, it must be not null.");
        Assert.notNull(b, "the argument b is required, it must be not null.");
        return a - b;
    }

    @Override
    public Integer mul(Integer a, Integer b) {
        Assert.notNull(a, "the argument a is required, it must be not null.");
        Assert.notNull(b, "the argument b is required, it must be not null.");
        return a * b;
    }


    @Override
    public Integer div(Integer a, Integer b) {
        Assert.notNull(a, "the argument a is required, it must be not null.");
        Assert.notNull(b, "the argument b is required, it must be not null.");
        if (b == 0) {
            throw new ArithmeticException("被除数不可为0");
        }
        return a / b;
    }

}
