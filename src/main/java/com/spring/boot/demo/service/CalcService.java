package com.spring.boot.demo.service;

/**
 * 计算公式：加减乘除
 *
 * @author jingLv
 * @date 2020/10/16
 */
public interface CalcService {

    /**
     * 加法计算
     *
     * @param a 数字a
     * @param b 数字b
     * @return 返回相加结果
     */
    Integer add(Integer a, Integer b);

    /**
     * 减法计算
     *
     * @param a 数字a
     * @param b 数字b
     * @return 返回相减结果
     */
    Integer sub(Integer a, Integer b);

    /**
     * 乘法计算
     *
     * @param a 数字a
     * @param b 数字b
     * @return 返回相乘结果
     */
    Integer mul(Integer a, Integer b);

    /**
     * 除法计算
     *
     * @param a 数字a
     * @param b 数字b
     * @return 返回相除结果
     */
    Integer div(Integer a, Integer b);
}
