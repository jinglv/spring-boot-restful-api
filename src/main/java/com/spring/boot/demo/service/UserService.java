package com.spring.boot.demo.service;

import com.spring.boot.demo.entity.UserEntity;

/**
 * @author jingLv
 * @date 2020/10/16
 */
public interface UserService {

    /**
     * 登录接口
     *
     * @param userEntity 用户信息
     * @return 返回登录信息
     */
    UserEntity login(UserEntity userEntity);


}
