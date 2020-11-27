package com.spring.boot.demo.service.impl;

import com.spring.boot.demo.BaseTest;
import com.spring.boot.demo.entity.UserInfoEntity;
import com.spring.boot.demo.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jingLv
 * @date 2020/11/16
 */
class UserInfoServiceImplTest extends BaseTest {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    void findUser() {
    }

    @Test
    void findUserFriend() {
    }

    @Test
    void queryUser() {
        save();
        List<UserInfoEntity> userInfoEntities = userInfoService.queryUser();
        userInfoEntities.forEach(System.out::println);
    }

    @Test
    void saveUser() {
        UserInfoEntity userInfo = new UserInfoEntity();
        userInfo.setUserId(1).setUserName("王小花").setPhone("13812345678").setEmail("xiaohua@qq.com");
        UserInfoEntity user = userInfoService.saveUser(userInfo);
        assertAll("result assertion", () -> assertEquals(user.getUserName(), "王小花"));
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    private UserInfoEntity save() {
        UserInfoEntity userInfo = new UserInfoEntity();
        userInfo.setUserId(1).setUserName("王小红").setPhone("13812345678").setEmail("xiao红@qq.com");
        return userInfoService.saveUser(userInfo);
    }
}