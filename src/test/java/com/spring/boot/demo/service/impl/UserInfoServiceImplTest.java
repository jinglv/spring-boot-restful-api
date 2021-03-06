package com.spring.boot.demo.service.impl;

import com.spring.boot.demo.BaseCase;
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
class UserInfoServiceImplTest extends BaseCase {

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
        assertAll("result assertion", () -> assertEquals(userInfoEntities.size(), 1));
        for (UserInfoEntity userInfoEntity : userInfoEntities) {
            delete(userInfoEntity);
        }
    }

    @Test
    void saveUser() {
        UserInfoEntity userInfo = new UserInfoEntity();
        userInfo.setUserId(1).setUserName("王小花").setPhone("13812345678").setEmail("xiaohua@qq.com");
        UserInfoEntity user = userInfoService.saveUser(userInfo);
        assertAll("result assertion", () -> assertEquals(user.getUserName(), "王小花"));
        delete(user);
    }

    @Test
    void updateUser() {
        UserInfoEntity userInfoEntity = save();
        userInfoEntity.setEmail("xiaohong@126.com").setPhone("18512341234");
        UserInfoEntity updateUser = userInfoService.updateUser(userInfoEntity.getUserId(), userInfoEntity);
        assertAll("result assertion", () -> assertEquals(updateUser.getPhone(), userInfoEntity.getPhone()));
        delete(updateUser);
    }

    @Test
    void deleteUser() {
        UserInfoEntity userInfoEntity = save();
        UserInfoEntity deleteUser = userInfoService.deleteUser(userInfoEntity.getUserId());
        assertAll("result assertion", () -> assertEquals(deleteUser.getUserId(), userInfoEntity.getUserId()));
    }

    private UserInfoEntity save() {
        UserInfoEntity userInfo = new UserInfoEntity();
        userInfo.setUserId(1).setUserName("王小红").setPhone("13812345678").setEmail("xiao红@qq.com");
        return userInfoService.saveUser(userInfo);
    }

    private void delete(UserInfoEntity userInfoEntity) {
        userInfoService.deleteUser(userInfoEntity.getUserId());
    }
}