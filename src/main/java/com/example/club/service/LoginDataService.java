package com.example.club.service;

import com.example.club.model.LoginData;

import java.util.List;

public interface LoginDataService {
    List<LoginData> queryLoginDataByUserId(Integer id);
    int insertNewRecord(LoginData loginData);
    int insertNewRecordByCurrentTime(LoginData loginData);
}
