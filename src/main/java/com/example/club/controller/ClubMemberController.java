package com.example.club.controller;

import com.example.club.model.ClubMember;
import com.example.club.model.User;
import com.example.club.service.ClubMemberService;
import com.example.club.service.ClubService;
import com.example.club.service.UserService;
import com.example.club.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Transactional
@RestController
public class ClubMemberController {
    @Resource
    UserService userService;

    @Resource
    ClubMemberService clubMemberService;

    @Resource
    ClubService clubService;

    @Transactional(readOnly = true)
    @GetMapping(value = "/user/{user-id}/joined-club")
    public Result getUserJoinedClubs(@PathVariable("user-id") Integer userId) {
        User user = userService.queryInfoById(userId);
        if (user == null) {
            return new Result(-1, "用户不存在", null);
        }
        return new Result(1, "查询成功", clubMemberService.getUserClubs(userId));
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/user/joined-club")
    public Result getMyJoinedClubs() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return new Result(-1, "未登录", null);
        }
        Integer userId = userService.queryInfoByNumber((String) subject.getPrincipal()).getUserId();
        return getUserJoinedClubs(userId);
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/user/created-club")
    public Result getMyCreatedClubs() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return new Result(-1, "未登录", null);
        }
        Integer userId = userService.queryInfoByNumber((String) subject.getPrincipal()).getUserId();
        return getUserCreatedClubs(userId);
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/user/{user-id}/created-club")
    public Result getUserCreatedClubs(@PathVariable("user-id") Integer userId) {
        return new Result(1,
                "查询成功",
                clubService.getCreatedClubs(userId));
    }

    @PostMapping(value = "/club/{club-id}/apply")
    public Result applyJoinClub(@PathVariable("club-id") Integer clubId,
                                @RequestBody ClubMember clubMember) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return new Result(-1, "未登录", null);
        }
        Integer userId = userService.queryInfoByNumber((String) subject.getPrincipal()).getUserId();
        if (clubMember.getUserId() != null && !clubMember.getUserId().equals(userId)) {
            return new Result(-1, "user id 不正确", null);
        }
        if (clubMember.getClubId() != null && !clubMember.getClubId().equals(clubId)) {
            return new Result(-1, "club id 不匹配", null);
        }
        if (clubService.findClubById(clubId) == null) {
            return new Result(-1, "社团不存在", null);
        }
        clubMember.setApplyTime(new Date());
        if (clubMember.getRole() == null) {
            clubMember.setRole(3);
        }
        clubMember.setUserId(userId);
        clubMember.setClubId(clubId);
        clubMemberService.newClubMember(clubMember);
        return new Result(1, "申请成功", null);
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/club/{club-id}/user/{user-id}/info")
    public Result getClubMemberInfo(@PathVariable("club-id") Integer clubId,
                                    @PathVariable("user-id") Integer userId) {
        ClubMember info = clubMemberService.getClubMemberInfo(clubId, userId);
        if (info == null) {
            return new Result(-1, "用户不在这个社团", null);
        }
        return new Result(1, "查存成功", info);
    }

    @PutMapping(value = "/club/{club-id}/user/{user-id}/info")
    public Result changeClubMemberInfo(@PathVariable("club-id") Integer clubId,
                                       @PathVariable("user-id") Integer userId,
                                       @RequestBody ClubMember clubMember) {
        clubMember.setClubId(clubId);
        clubMember.setUserId(userId);

        if (clubMemberService.changeClubMemberInfo(clubMember) != 1) {
            return new Result(-1, "未找到该成员", null);
        }
        return new Result(1, "修改成功", null);
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/club/{club-id}/apply")
    public Result getClubApplyUsers(@PathVariable("club-id") Integer clubId) {
        return new Result(1,
                "查询成功",
                clubMemberService.getApplyMembers(clubId));
    }

    @PutMapping(value = "/club/{club-id}/user/{user-id}/role")
    public Result setMemberRole(@PathVariable("club-id") Integer clubId,
                                @PathVariable("user-id") Integer userId,
                                @RequestBody ClubMember clubMember) {

        clubMember.setClubId(clubId);
        clubMember.setUserId(userId);

        if (clubMemberService.setUserRole(clubMember) == 1) {
            return new Result(1, "成功", null);
        } else {
            return new Result(-1, "用户不在社团里", null);
        }

    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/club/{club-id}/member")
    public Result getClubMembers(@PathVariable("club-id") Integer clubId) {
        if (clubService.findClubById(clubId) == null) {
            return new Result(-1, "社团不存在", null);
        }
        return new Result(1, "查询成功", clubMemberService.getClubMembers(clubId));
    }

    @DeleteMapping(value = "/club/{club-id}/user/{user-id}")
    public Result deleteClubMember(@PathVariable("club-id") Integer clubId,
                                   @PathVariable("user-id") Integer userId) {
        return new Result(1,
                "删除成功",
                clubMemberService.deleteMember(clubId, userId));
    }
}
