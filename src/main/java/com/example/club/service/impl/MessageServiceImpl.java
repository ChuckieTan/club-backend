package com.example.club.service.impl;

import com.example.club.mapper.MessageMapper;
import com.example.club.model.MessageWithBLOBs;
import com.example.club.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Resource
    MessageMapper messageMapper;

    @Override
    public int postMessage(MessageWithBLOBs message) {
        return messageMapper.insertSelective(message);
    }

    @Override
    public int updateMessage(MessageWithBLOBs message) {
        return messageMapper.updateByPrimaryKeySelective(message);
    }

    @Override
    public int deleteMessage(Integer id) {
        return messageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<MessageWithBLOBs> getClubMessages(Integer clubId) {
        return messageMapper.selectByClubId(clubId);
    }

    @Override
    public MessageWithBLOBs getMesssageById(Integer messageId) {
        return messageMapper.selectByPrimaryKey(messageId);
    }
}
