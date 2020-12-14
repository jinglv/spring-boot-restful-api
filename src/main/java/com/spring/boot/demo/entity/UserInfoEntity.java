package com.spring.boot.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 用户实体--资源
 *
 * @author jingLv
 * @date 2020/11/16
 */
@Data
@Accessors(chain = true)
public class UserInfoEntity implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5830519218561458583L;
    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 好友列表
     */
    private List<UserInfoEntity> friends;
}
