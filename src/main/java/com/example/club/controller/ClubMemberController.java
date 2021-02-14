package com.example.club.controller;

import com.example.club.service.ClubMemberService;
import com.example.club.service.ClubService;
import com.example.club.service.UserService;
import com.example.club.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ClubMemberController {
    @Resource
    UserService userService;

    @Resource
    ClubMemberService clubMemberService;

    @Resource
    ClubService clubService;

    @GetMapping(value = "/user/{user-id}/joined-club")
    public Result getMyJoinedClub(@PathVariable("user-id") Integer userId) {

    }
}
