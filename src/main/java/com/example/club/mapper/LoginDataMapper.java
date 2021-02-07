package com.example.club.mapper;

import com.example.club.model.LoginData;

import java.util.List;

public interface LoginDataMapper {
    int deleteByPrimaryKey(Integer loginId);

    int insert(LoginData record);

    int insertSelective(LoginData record);

    LoginData selectByPrimaryKey(Integer loginId);

    int updateByPrimaryKeySelective(LoginData record);

    int updateByPrimaryKey(LoginData record);

    List<LoginData> selectByUserId(Integer userId);
}