package com.example.club.controller;

import com.example.club.model.User;
import com.example.club.service.ClubService;
import com.example.club.util.ResultType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ClubController {
    @Resource
    ClubService clubService;

    @GetMapping(value = "/club")
    public ResultType createClub() {
        return new ResultType(1, "", new User());
    }
}
