package com.spring.boot.demo.service.impl;

import com.spring.boot.demo.common.exception.CustomException;
import com.spring.boot.demo.entity.UserEntity;
import com.spring.boot.demo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author jingLv
 * @date 2020/10/16
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserEntity login(UserEntity userEntity) {
        // 模拟根据接收的用户名和密码查询数据库，在此写死用户名和密码
        UserEntity user = new UserEntity();
        user.setUsername("xiaohong").setPassword("123123");
        if (user != null) {
            return user;
        }
        throw new CustomException("用户不存在--");
    }
}
