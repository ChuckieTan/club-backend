package com.example.club.controller;

import com.example.club.model.Club;
import com.example.club.model.ClubWithBLOBs;
import com.example.club.service.ClubService;
import com.example.club.service.UserService;
import com.example.club.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class ClubController {
    @Resource
    ClubService clubService;

    public static Logger logger = LoggerFactory.getLogger(ClubController.class);
    @Resource
    UserService userService;

    @GetMapping(value = "/club")
    public Result getAllClubsInfo() {
        return new Result(1, "查询成功", clubService.findClubs());
    }

    @GetMapping(value = "/club/{club-id}/info")
    public Result getClubInfoById(@PathVariable("club-id") Integer clubId) {
        Result result = null;
        Club club = clubService.findClubById(clubId);
        if (club == null) {
            result = new Result(-1, "没有这个社团", null);
        } else {
            result = new Result(1, "查询成功", club);
        }
        return result;
    }


    @PostMapping(value = "/club")
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

    @PutMapping(value = "/club/{club-id}/info")
    public Result changeClubInfo(@PathVariable("club-id") Integer clubId,
                                 @RequestBody ClubWithBLOBs club) {
        ClubWithBLOBs dbClub = clubService.findClubById(clubId);
        if (club.getClubId() != null && !club.getClubId().equals(dbClub.getClubId())) {
            return new Result(-1, "不能修改id", null);
        }
        if (club.getName() != null && !club.getName().equals(dbClub.getName())) {
            return new Result(-1, "不能修改名字", null);
        }
        if (club.getType() != null && !club.getType().equals(dbClub.getType())) {
            return new Result(-1, "不能修改类型", null);
        }
        if (club.getInitiatorId() != null && !club.getInitiatorId().equals(dbClub.getInitiatorId())) {
            return new Result(-1, "不能修改发起人", null);
        }
        if (club.getPracticallyAnalysis() != null && !club.getPracticallyAnalysis().equals(dbClub.getPracticallyAnalysis())) {
            return new Result(-1, "不能修改可行性分析", null);
        }

        clubService.changeClubInfo(club);
        return new Result(1, "修改成功", null);
    }
}
