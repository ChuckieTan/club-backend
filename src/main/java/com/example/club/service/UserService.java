package com.example.club.service;

import com.example.club.model.User;
import com.example.club.util.PageRequest;
import com.example.club.util.PageResult;

public interface UserService {
    User queryInfoById(Integer id);

    User queryInfoByNumber(String number);

    int changeInfoById(User user);

    int insert(User user);

    PageResult searchUserByNumber(String number, PageRequest pageRequest);
}
