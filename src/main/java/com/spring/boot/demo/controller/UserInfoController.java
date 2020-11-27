package com.spring.boot.demo.controller;

import com.spring.boot.demo.common.response.ApiResponse;
import com.spring.boot.demo.entity.UserInfoEntity;
import com.spring.boot.demo.service.UserInfoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * API Controller
 *
 * @author jingLv
 * @date 2020/11/16
 */
@RestController
@ResponseBody
@Slf4j
@RequestMapping("/v1/user")
@Api(tags = "用户信息相关接口", value = "UserController", protocols = "JSON")
public class UserInfoController {

    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    /**
     * 查询指定用户信息
     *
     * @param userId 用户id
     * @return 返回接口结果信息
     */
    @GetMapping(value = "/user/{userId}")
    public ApiResponse<Object> findUser(@PathVariable("userId") Integer userId) {
        log.info("查询指定用户信息：userId={}", userId);
        return ApiResponse.ofSuccess(userInfoService.findUser(userId));
    }

    /**
     * 查询指定用户指定好友信息
     *
     * @param userId   用户id
     * @param friendId 用户id
     * @return 返回接口结果信息
     */
    @GetMapping(value = "/user/{userId}/friend/{friendId}")
    public ApiResponse<Object> findUserFriend(@PathVariable("userId") Integer userId, @PathVariable("friendId") Integer friendId) {
        log.info("查询指定用户的好友信息：userId={}， friendId={}", userId, friendId);
        return ApiResponse.ofSuccess(userInfoService.findUserFriend(userId, friendId));
    }

    /**
     * 查询所用用户信息
     *
     * @return 返回接口结果信息
     */
    @GetMapping(value = "/user")
    public ApiResponse<Object> queryUser() {
        log.info("查询所有用户信息");
        return ApiResponse.ofSuccess(userInfoService.queryUser());
    }

    /**
     * 保存用户信息
     *
     * @param user 用户信息
     * @return 返回接口结果信息
     */
    @PostMapping(value = "/user")
    public ApiResponse<Object> saveUser(@RequestBody UserInfoEntity user) {
        log.info("保存用户信息：user={}", user);
        return ApiResponse.ofSuccess(userInfoService.saveUser(user));
    }

    /**
     * 更新用户信息
     *
     * @param userId 用户id
     * @param user   用户信息
     * @return 返回接口结果信息
     */
    @PutMapping(value = "/user/{userId}")
    public ApiResponse<Object> updateUser(@PathVariable("userId") Integer userId, @RequestBody UserInfoEntity user) {
        log.info("更新用户信息：userId={},user={}", userId, user);
        return ApiResponse.ofSuccess(userInfoService.updateUser(userId, user));
    }

    /**
     * 删除用户信息
     *
     * @param userId 用户id
     * @return 返回接口结果信息
     */
    @DeleteMapping(value = "/user/{userId}")
    public ApiResponse<Object> deleteUser(@PathVariable("userId") Integer userId) {
        log.info("删除用户信息：userId={}", userId);
        return ApiResponse.ofSuccess(userInfoService.deleteUser(userId));
    }
}
