package com.spring.boot.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author jinglv
 */
@Data
@Accessors(chain = true)
public class UserEntity implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6537536297353335957L;
    /**
     * 主键
     */
    private String id;
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    private String username;
    /**
     * 用户密码
     */
    @NotNull(message = "用户密码不能为空")
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
