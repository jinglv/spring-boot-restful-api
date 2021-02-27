package com.spring.boot.demo.service.impl;

import com.spring.boot.demo.BaseCase;
import com.spring.boot.demo.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jingLv
 * @date 2020/12/14
 */
class UserServiceImplTest extends BaseCase {

    @Autowired
    private UserServiceImpl userService;

    @Test
    void login() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("xiaohong").setPassword("123123");
        UserEntity login = userService.login(userEntity);
        assertAll("result assertion", () -> assertEquals(login.getUsername(), userEntity.getUsername()));
    }
}