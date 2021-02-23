package com.example.club.controller;

import com.example.club.model.LoginData;
import com.example.club.service.LoginDataService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@RestController
public class LoginDataController {

    @Resource
    LoginDataService loginDataService;

    @Transactional(readOnly = true)
    @GetMapping(value = "/user/{user-id}/login-data")
    public List<LoginData> queryLoginData(@PathVariable("user-id") Integer userId) {
        return loginDataService.queryLoginDataByUserId(userId);
    }
}
