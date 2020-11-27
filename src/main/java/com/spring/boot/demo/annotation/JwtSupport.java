package com.spring.boot.demo.annotation;

import java.lang.annotation.*;

/**
 * 如果需要jwt拦截，则在controller类添加此注解
 *
 * @author jinglv
 * @date 2020/10/20
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JwtSupport {

}
