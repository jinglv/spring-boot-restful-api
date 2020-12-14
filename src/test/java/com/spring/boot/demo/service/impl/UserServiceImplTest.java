package com.spring.boot.demo.service.impl;

import com.spring.boot.demo.BaseTest;
import com.spring.boot.demo.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jingLv
 * @date 2020/12/14
 */
class UserServiceImplTest extends BaseTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    void login() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("xiaohong").setPassword("123123");
        UserEntity login = userService.login(userEntity);
        assertAll("result assertion", () -> assertEquals(login.getUsername(), userEntity.getUsername()));
    }

//    @Test
//    void loginFail() {
//        UserEntity userEntity = new UserEntity();
//        // 用户名不存在
//        userEntity.setUsername("xiaohei").setPassword("123123");
//        Throwable exception = assertThrows(CustomException.class, () -> {
//            UserEntity login = userService.login(userEntity);
//        });
//        assertEquals("用户不存在--", exception.getMessage());
//    }
}