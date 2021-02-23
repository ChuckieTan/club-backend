package com.example.club.controller;

import com.example.club.service.ClubService;
import com.example.club.util.PageRequest;
import com.example.club.util.Result;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Transactional(readOnly = true)
public class SearchController {
    @Resource
    ClubService clubService;

    @GetMapping(value = "/search/club/{word}")
    public Result searchClubs(@PathVariable("word") String word,
                              @RequestBody PageRequest pageRequest) {
        return new Result(1, "搜索成功", clubService.searchClubsByPage(word, pageRequest));
    }
}
