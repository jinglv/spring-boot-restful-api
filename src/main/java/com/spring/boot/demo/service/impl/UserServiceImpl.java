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
        String userName = "xiaohong";
        String password = "123123";
        // 模拟根据接收的用户名和密码查询数据库，在此写死用户名和密码
        if (userEntity.getUsername().equals(userName) && userEntity.getPassword().equals(password)) {
            UserEntity user = new UserEntity();
            return user.setId("1").setUsername(userName).setPassword(password);
        }
        throw new CustomException("用户不存在");
    }
}
