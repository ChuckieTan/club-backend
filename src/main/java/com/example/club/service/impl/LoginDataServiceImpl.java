package com.example.club.service.impl;

import com.example.club.mapper.LoginDataMapper;
import com.example.club.model.LoginData;
import com.example.club.service.LoginDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class LoginDataServiceImpl implements LoginDataService {

    @Resource
    LoginDataMapper loginDataMapper;

    @Override
    public List<LoginData> queryLoginDataByUserId(Integer id) {
        return loginDataMapper.selectByUserId(id);
    }

    @Override
    public int insertNewRecord(LoginData loginData) {
        loginData.setLoginId(null);
        return loginDataMapper.insertSelective(loginData);
    }

    @Override
    public int insertNewRecordByCurrentTime(LoginData loginData) {
        loginData.setLoginId(null);
        loginData.setTime(new Date());
        return loginDataMapper.insertSelective(loginData);
    }
}
