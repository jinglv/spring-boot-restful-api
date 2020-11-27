package com.spring.boot.demo.service;

import com.spring.boot.demo.entity.UserInfoEntity;

import java.util.List;

/**
 * API Service
 *
 * @author jingLv
 * @date 2020/11/16
 */
public interface UserInfoService {

    /**
     * 根据userId查询指定用户
     *
     * @param userId 用户id
     * @return 用户信息
     */
    UserInfoEntity findUser(Integer userId);

    /**
     * 根据userId查询指定好友信息
     *
     * @param userId   用户id
     * @param friendId 用户好友id
     * @return 用户好友信息
     */
    UserInfoEntity findUserFriend(Integer userId, Integer friendId);

    /**
     * 查询所有用户信息
     *
     * @return 返回用户信息列表
     */
    List<UserInfoEntity> queryUser();

    /**
     * 保存用户信息
     *
     * @param userInfoEntity 用户信息
     * @return 返回保存结果
     */
    UserInfoEntity saveUser(UserInfoEntity userInfoEntity);

    /**
     * 更新指定的用户信息
     *
     * @param userId         用户id
     * @param userInfoEntity 用户信息
     * @return 返回更新结果
     */
    UserInfoEntity updateUser(Integer userId, UserInfoEntity userInfoEntity);

    /**
     * 删除指定用户信息
     *
     * @param userId 用户id
     * @return 返回删除结果
     */
    UserInfoEntity deleteUser(Integer userId);
}
