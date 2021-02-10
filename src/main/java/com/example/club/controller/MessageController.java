package com.example.club.controller;

import com.example.club.model.MessageWithBLOBs;
import com.example.club.model.User;
import com.example.club.service.ClubService;
import com.example.club.service.MessageReadService;
import com.example.club.service.MessageService;
import com.example.club.service.UserService;
import com.example.club.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class MessageController {
    @Resource
    MessageService messageService;

    @Resource
    MessageReadService messageReadService;

    @Resource
    ClubService clubService;

    @Resource
    UserService userService;

    @PostMapping(value = "/message")
    public Result postMessage(MessageWithBLOBs message) {
        Result result = null;
        message.setReleaseTime(new Date());
        if (message.getTitle() == null) {
            return new Result(-1, "没有标题", null);
        }
        if (message.getAuthorId() == null) {
            return new Result(-1, "没有作者", null);
        }

        Integer clubId = message.getClubId();
        if (clubId == null || clubService.findClubById(clubId) == null) {
            return new Result(-1, "社团不存在", null);
        }
        if (messageService.postMessage(message) != 1) {
            return new Result(-1, "未知错误", null);
        } else {
            messageReadService.initMessage(clubId, message.getMessageId());
            return new Result(1, "发布成功", message.getMessageId());
        }
    }

    @PutMapping(value = "/message/{message-id}")
    public Result modifyMessage(@PathVariable("message-id") Integer messageId,
                                MessageWithBLOBs message) {
        Result result = null;
        if (message.getMessageId() != null &&
                !messageId.equals(message.getMessageId())) {
            return new Result(-1, "不能修改id", null);
        }
        message.setMessageId(messageId);
        message.setReleaseTime(null);
        if (messageService.updateMessage(message) != 1) {
            return new Result(-1, "未知错误", null);
        }
        return new Result(1, "修改成功", null);
    }

    @GetMapping(value = "/user/messages")
    public Result getUserMessages() {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        if (username == null) {
            return new Result(-1, "未登录", null);
        }
        User user = userService.queryInfoByNumber(username);
        if (user == null) {
            return new Result(-1, "请重新登录", null);
        }
        return new Result(1,
                "查询成功",
                messageReadService.getUserMessages(user.getUserId()));
    }

    @DeleteMapping(value = "/message/{message-id}")
    public Result deleteMessage(@PathVariable("message-id") Integer messageId) {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null) {
            return new Result(-1, "未登录", null);
        }
        if (messageService.getMesssageById(messageId) == null) {
            return new Result(-1, "消息不存在", null);
        }
        if (messageService.deleteMessage(messageId) != 1) {
            return new Result(1, "未知错误", null);
        }
        return new Result(1, "删除成功", null);
    }
}
