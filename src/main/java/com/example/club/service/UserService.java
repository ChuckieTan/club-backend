package com.example.club.service;

import com.example.club.model.User;

public interface UserService {
    User queryInfoById(Integer id);
    User queryInfoByNumber(String number);
    int changeInfoById(User user);
    int insert(User user);
}
