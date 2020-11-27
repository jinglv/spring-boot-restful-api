package com.spring.boot.demo.service.impl;

import com.spring.boot.demo.common.exception.CustomException;
import com.spring.boot.demo.entity.UserInfoEntity;
import com.spring.boot.demo.service.UserInfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * @author jingLv
 * @date 2020/11/16
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    /**
     * 数据存储
     */
    private static final ConcurrentHashMap<Integer, UserInfoEntity> DATA = new ConcurrentHashMap<>();

    /**
     * 查询指定用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    @Override
    public UserInfoEntity findUser(Integer userId) {
        return DATA.get(userId);
    }

    /**
     * 查询指定用户的好友信息
     *
     * @param userId   用户id
     * @param friendId 用户好友id
     * @return 用户信息
     */
    @Override
    public UserInfoEntity findUserFriend(Integer userId, Integer friendId) {
        UserInfoEntity friend = null;
        UserInfoEntity user = DATA.get(userId);
        if (user != null) {
            friend = Optional.ofNullable(user.getFriends())
                    .map(List::stream)
                    .orElseGet(Stream::empty)
                    .filter(friendItem -> friendItem.getUserId().equals(friendId))
                    .findFirst().get();
        }
        return friend;
    }

    /**
     * 查询所有用户信息
     *
     * @return 返回用户列表
     */
    @Override
    public List<UserInfoEntity> queryUser() {
        return new ArrayList<>(DATA.values());
    }

    /**
     * 保存用户信息
     *
     * @param userInfoEntity 用户信息
     * @return 保存结果
     */
    @Override
    public UserInfoEntity saveUser(UserInfoEntity userInfoEntity) {
        if (DATA.get(userInfoEntity.getUserId()) != null) {
            throw new CustomException("用户已存在");
        }
        DATA.put(userInfoEntity.getUserId(), userInfoEntity);
        return userInfoEntity;
    }

    /**
     * 更新用户信息
     *
     * @param userId         用户id
     * @param userInfoEntity 用户信息
     * @return 更新结果
     */
    @Override
    public UserInfoEntity updateUser(Integer userId, UserInfoEntity userInfoEntity) {
        if (DATA.get(userId) == null) {
            throw new CustomException("用户不存在！");
        }
        if (!userId.equals(userInfoEntity.getUserId())) {
            throw new CustomException("用户编号不一致");
        }
        DATA.put(userId, userInfoEntity);
        return userInfoEntity;
    }

    /**
     * 删除用户信息
     *
     * @param userId 用户id
     * @return 删除结果
     */
    @Override
    public UserInfoEntity deleteUser(Integer userId) {
        return DATA.remove(userId);
    }
}
