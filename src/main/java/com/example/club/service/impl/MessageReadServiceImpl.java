package com.example.club.service.impl;

import com.example.club.mapper.MessageReadMapper;
import com.example.club.model.MessageRead;
import com.example.club.service.MessageReadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MessageReadServiceImpl implements MessageReadService {
    @Resource
    MessageReadMapper messageReadMapper;

    @Override
    public void initMessage(List<Integer> userIds, Integer messageId) {
        for (Integer userId : userIds) {
            messageReadMapper.insertSelective(new MessageRead(userId, messageId, null));
        }
    }

    @Override
    public List<MessageRead> getUserMessages(Integer userId) {
        return messageReadMapper.selectByUserId(userId);
    }

    @Override
    public void deleteMessage(Integer messageId) {
        messageReadMapper.deleteByMessageId(messageId);
    }

    @Override
    public int readMessage(Integer userId, Integer messageId) {
        return messageReadMapper.updateByPrimaryKeySelective(
                new MessageRead(userId, messageId, new Date())
        );
    }
}
