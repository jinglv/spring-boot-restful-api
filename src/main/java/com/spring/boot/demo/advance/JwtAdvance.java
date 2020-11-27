package com.spring.boot.demo.advance;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.spring.boot.demo.annotation.JwtSupport;
import com.spring.boot.demo.common.exception.CustomException;
import com.spring.boot.demo.common.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * jwt认证通知
 *
 * @author jinglv
 * @date 2020/10/18
 */
@Slf4j
@Component
@ControllerAdvice(annotations = {JwtSupport.class})
public class JwtAdvance {

    @ModelAttribute
    public void preHandle(HttpServletRequest request) {
        // 获取请求头中的令牌
        String token = request.getHeader("token");
        try {
            // 验证token有效性
            JwtUtils.verify(token);
            //放行请求
        } catch (Exception e) {
            if (e instanceof TokenExpiredException) {
                throw new CustomException("token已经过期");
            } else if (e instanceof SignatureVerificationException) {
                throw new CustomException("签名错误");
            } else if (e instanceof AlgorithmMismatchException) {
                throw new CustomException("加密算法不匹配");
            } else {
                throw new CustomException("无效token");
            }
        }
    }
}
