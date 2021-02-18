package com.example.club.controller;

import com.example.club.model.ClubMember;
import com.example.club.model.User;
import com.example.club.service.ClubMemberService;
import com.example.club.service.ClubService;
import com.example.club.service.UserService;
import com.example.club.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class ClubMemberController {
    @Resource
    UserService userService;

    @Resource
    ClubMemberService clubMemberService;

    @Resource
    ClubService clubService;

    @GetMapping(value = "/user/{user-id}/joined-club")
    public Result getUserJoinedClubs(@PathVariable("user-id") Integer userId) {
        User user = userService.queryInfoById(userId);
        if (user == null) {
            return new Result(-1, "用户不存在", null);
        }
        return new Result(1, "查询成功", clubMemberService.getUserClubs(userId));
    }

    @GetMapping(value = "/user/joined-club")
    public Result getMyJoinedClubs() {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null) {
            return new Result(-1, "未登录", null);
        }
        Integer userId = userService.queryInfoByNumber((String) subject.getPrincipal()).getUserId();
        return getUserJoinedClubs(userId);
    }

    @GetMapping(value = "/user/created-club")
    public Result getMyCreatedClubs() {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null) {
            return new Result(-1, "未登录", null);
        }
        Integer userId = userService.queryInfoByNumber((String) subject.getPrincipal()).getUserId();
        return getUserCreatedClubs(userId);
    }

    @GetMapping(value = "/user/{user-id}/created-club")
    public Result getUserCreatedClubs(@PathVariable("user-id") Integer userId) {
        return new Result(1,
                "查询成功",
                clubService.getCreatedClubs(userId));
    }

    @PostMapping(value = "/api/club/{club-id}/apply")
    public Result applyJoinClub(@PathVariable("club-id") Integer clubId,
                                @RequestBody ClubMember clubMember) {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null) {
            return new Result(-1, "未登录", null);
        }
        Integer userId = userService.queryInfoByNumber((String) subject.getPrincipal()).getUserId();
        if (clubMember.getUserId() != null && !clubMember.getUserId().equals(userId)) {
            return new Result(-1, "user id 不正确", null);
        }
        if (clubMember.getClubId() != null && !clubMember.getClubId().equals(clubId)) {
            return new Result(-1, "club id 不匹配", null);
        }
        clubMember.setApplyTime(new Date());
        if (clubMember.getRole() == null) {
            clubMember.setRole(3);
        }
        clubMemberService.newClubMember(clubMember);
        return new Result(1, "申请成功", null);
    }

    @GetMapping(value = "/club/{club-id}/apply")
    public Result getClubApplyUsers(@PathVariable("club-id") Integer clubId) {
        return new Result(1,
                "查询成功",
                clubMemberService.getApplyMembers(clubId));
    }
}
