package com.spring.boot.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author jinglv
 */
@Data
@Accessors(chain = true)
public class UserEntity {
    /**
     * 主键
     */
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 注册时间
     */
    private String registerTime;
    /**
     * 创建时间
     */
    private String createTime;
}
