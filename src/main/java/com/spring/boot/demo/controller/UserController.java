package com.spring.boot.demo.controller;

import com.spring.boot.demo.common.exception.CustomException;
import com.spring.boot.demo.common.response.ApiResponse;
import com.spring.boot.demo.common.utils.JwtUtils;
import com.spring.boot.demo.entity.UserEntity;
import com.spring.boot.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jingLv
 * @date 2020/10/16
 */
@RestController
@Slf4j
@RequestMapping("/v1/user")
@Api(tags = "用户认证相关接口", value = "UserController", protocols = "JSON")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "用户登录接口",
            notes = "用户登录",
            produces = "application/json, application/xml",
            consumes = "application/json, application/xml",
            httpMethod = "GET",
            response = List.class)
    @GetMapping(value = "login", produces = "application/json;charset=UTF-8")
    public ApiResponse<Object> login(@RequestBody UserEntity user) {
        log.info("用户名：{}", user.getUsername());
        log.info("密码：{}", user.getPassword());

        try {
            Map<String, Object> info = new HashMap<>(16);
            UserEntity userInfo = userService.login(user);
            Map<String, String> payload = new HashMap<>(16);
            payload.put("id", userInfo.getId());
            payload.put("userName", userInfo.getUsername());
            // 生成JWT令牌
            String token = JwtUtils.getToken(payload);
            info.put("token", token);
            return ApiResponse.ofSuccess(info);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.ofException(new CustomException());
        }
    }
}
