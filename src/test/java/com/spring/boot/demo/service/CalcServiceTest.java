package com.spring.boot.demo.service;

import com.spring.boot.demo.BaseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author jingLv
 * @date 2020/10/16
 */
class CalcServiceTest extends BaseCase {

    @Autowired
    private CalcService calcService;

    private final Integer a = 2;
    private final Integer b = 1;

    @Test
    @DisplayName("计算加法正确执行")
    void add() {
        Integer result = calcService.add(a, b);
        assertAll("add result", () -> assertEquals(3, result));
    }

    @Test
    @DisplayName("计算加法异常执行传入的数字为null")
    void addNumber() {
        try {
            Integer result = calcService.add(null, null);
        } catch (IllegalArgumentException e) {
            // 注意：同时传null的时候，代码执行，第一行抛出了异常，就不会执行下一行了
            Assertions.assertThat(e).isInstanceOf(IllegalArgumentException.class).hasMessage("the argument a is required, it must be not null.");
        }
    }

    @Test
    @DisplayName("计算加法异常执行传入的加数B为null")
    void addForB() {
        try {
            Integer result = calcService.add(a, null);
        } catch (IllegalArgumentException e) {
            // 注意：同时传null的时候，代码执行，第一行抛出了异常，就不会执行下一行了
            Assertions.assertThat(e).isInstanceOf(IllegalArgumentException.class).hasMessage("the argument b is required, it must be not null.");
        }
    }

    @Test
    @DisplayName("计算减法正确执行")
    void sub() {
        Integer result = calcService.sub(a, b);
        assertAll("sub result", () -> assertEquals(1, result));
    }

    @Test
    @DisplayName("计算乘法正确执行")
    void mul() {
        Integer result = calcService.mul(a, b);
        assertAll("mul result", () -> assertEquals(2, result));
    }

    @Test
    @DisplayName("计算除法正确执行")
    void div() {
        Integer result = calcService.div(a, b);
        assertAll("div result", () -> assertEquals(2, result));
    }

    @Test
    @DisplayName("计算除法为0的异常测试")
    void divException() {
        try {
            Integer result = calcService.div(a, 0);
        } catch (ArithmeticException e) {
            Assertions.assertThat(e).isInstanceOf(ArithmeticException.class).hasMessage("被除数不可为0");
        }
    }
}