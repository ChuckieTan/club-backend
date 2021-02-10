package com.example.club.controller;

import com.example.club.model.User;
import com.example.club.service.UserService;
import com.example.club.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
public class UserController {
    @Resource
    UserService userService;

    @GetMapping(value = "/user/{user-id}/info")
    public Result queryInfo(@PathVariable("user-id") Integer userId) {
        User user = userService.queryInfoById(userId);
        Result result = null;
        if (user != null) {
            result = new Result(1, "查询成功", user);
        } else {
            result = new Result(-1, "用户不存在", null);
        }
        return result;
    }

    @PutMapping(value = "/user/{user-id}/info")
    public Result changeInfo(@RequestBody User user,
                             @PathVariable("user-id") Integer id) {
        Result result = null;
        User dbUser = userService.queryInfoById(id);
        if (user.getUserId() != null && !Objects.equals(user.getUserId(), id)) {
            result = new Result(-1, "无法修改id", null);
        } else if (user.getNumber() != null &&
                !Objects.equals(dbUser.getNumber(), user.getNumber())) {
            result = new Result(-1, "无法修改学号", null);
        } else {
            user.setUserId(id);
            int row = userService.changeInfoById(user);
            if (row != 1) {
                result = new Result(-1, "未知错误", null);
            } else {
                result = new Result(1, "修改信息成功", null);
            }
        }
        return result;
    }

    @GetMapping(value = "/user/logged-user")
    public Result loggedUser() {
        Result result = null;
        Subject subject = SecurityUtils.getSubject();
        String userNumber = (String) subject.getPrincipal();
        if (userNumber == null) {
            result = new Result(-1, "未登录", null);
        } else {
            result = new Result(1,
                    "查询成功",
                    userService.queryInfoByNumber(userNumber).getUserId());
        }
        return result;
    }

    @GetMapping(value = "/user/info")
    public Result queryLoginInfo() {
        Result result = null;

        Subject subject = SecurityUtils.getSubject();
        String userNumber = (String) subject.getPrincipal();
        if (userNumber == null) {
            result = new Result(-1, "未登录", null);
        } else {
            User user = userService.queryInfoByNumber(userNumber);
            if (user != null) {
                result = new Result(1, "查询成功", user);
            } else {
                result = new Result(-1, "用户不存在", null);
            }
        }
        return result;
    }

    @PutMapping(value = "/user/info")
    public Result changeLoginInfo(@RequestBody User user) {
        Result result = null;

        System.out.println(user);

        Subject subject = SecurityUtils.getSubject();
        String loggedUserNumber = (String) subject.getPrincipal();
        if (loggedUserNumber == null) {
            result = new Result(-1, "未授权", null);
        } else {
            User dbUser = userService.queryInfoByNumber(loggedUserNumber);
            if (dbUser == null) {
                result = new Result(-1, "请重新登录", null);
            } else {
                if (user.getUserId() != null &&
                        !Objects.equals(user.getUserId(), dbUser.getUserId())) {
                    result = new Result(-1, "无法修改id", null);
                } else if (user.getNumber() != null &&
                        !Objects.equals(dbUser.getNumber(), user.getNumber())) {
                    result = new Result(-1, "无法修改学号", null);
                } else {
                    user.setUserId(dbUser.getUserId());

                    int row = userService.changeInfoById(user);
                    if (row != 1) {
                        result = new Result(-1, "未知错误", null);
                    } else {
                        result = new Result(1, "修改信息成功", null);
                    }
                }
            }
        }
        return result;
    }

    @PostMapping(value = "/register")
    public Result register(@RequestBody User user) {
        Result result;
        if (userService.queryInfoByNumber(user.getNumber()) == null) {
            userService.insert(user);
            result = new Result(1, "注册成功", user.getUserId());
        } else {
            result = new Result(-1, "用户名已存在", null);
        }
        return result;
    }

}
