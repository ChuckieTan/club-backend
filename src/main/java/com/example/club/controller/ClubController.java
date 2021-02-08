package com.example.club.controller;

import com.example.club.model.ClubWithBLOBs;
import com.example.club.service.ClubService;
import com.example.club.service.UserService;
import com.example.club.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ClubController {
    @Resource
    ClubService clubService;

    public static Logger logger = LoggerFactory.getLogger(ClubController.class);
    @Resource
    UserService userService;

    @PostMapping(value = "/club/create")
    public Result createClub(@RequestBody ClubWithBLOBs club) {
        club.setClubId(null);
        club.setProgress(1);
        Result result = null;
        if (club.getName() == null) {
            result = new Result(-1, "社团名称为空", null);
        } else if (club.getInitiatorId() != null
                && userService.queryInfoById(club.getInitiatorId()) == null) {
            result = new Result(-1, "创始人id不正确", null);
        } else {
            try {
                logger.info("创建社团: " + club);
                if (clubService.createClub(club) != 1) {
                    result = new Result(-1, "社团未创建", null);
                    logger.info("社团未创建");
                } else {
                    result = new Result(1, "社团创建成功", club);
                    logger.info("社团创建成功");
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = new Result(-1, "社团未创建", null);
                logger.error("社团未创建: " + club);
            }
        }
        return result;
    }
}
