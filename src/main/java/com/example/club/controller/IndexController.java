package com.example.club.controller;

import com.example.club.util.BeanUtil;
import com.example.club.model.Club;
import com.example.club.service.ClubService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @RequestMapping("/{id}/{age}")
    @ResponseBody
    public String sayId(@PathVariable("id") Integer id,
                      @PathVariable("age") Integer age) {
        return "Hello " + id + " " + age;
    }

    @ResponseBody
    @RequestMapping("/asd")
    public Club say() {
        ClubService clubService = (ClubService) BeanUtil.getBean("clubServiceImpl");
        return null;
    }
}
