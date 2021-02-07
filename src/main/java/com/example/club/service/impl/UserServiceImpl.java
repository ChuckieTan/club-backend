package com.example.club.service.impl;

import com.example.club.mapper.UserMapper;
import com.example.club.model.User;
import com.example.club.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User queryInfoById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User queryInfoByNumber(String number) {
        return userMapper.selectByNumber(number);
    }

    @Override
    public int changeInfoById(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int insert(User user) {
        return userMapper.insertSelective(user);
    }
}
