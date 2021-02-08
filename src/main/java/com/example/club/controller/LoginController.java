package com.example.club.controller;

import com.example.club.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
    @PostMapping(value = "/login")
    public Result login(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
        Subject subject = SecurityUtils.getSubject();

        // 创建用户认证的身份令牌，并设置帐号密码
        var usernamePasswordToken = new UsernamePasswordToken(username, password);
        Result result = null;
        try {
            // 如果已登录则登出
            subject.logout();
            subject.login(usernamePasswordToken);
            result = new Result(1, "login success", null);
        } catch (UnknownAccountException e) {
            result = new Result(0, "unknown account", null);
        } catch (AuthenticationException e) {
            result = new Result(0, "username or password was wrong", null);
        }
        return result;
    }

    @PostMapping(value = "/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return null;
    }
}
