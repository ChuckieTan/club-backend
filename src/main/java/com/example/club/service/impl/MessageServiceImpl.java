package com.example.club.service.impl;

import com.example.club.mapper.MessageMapper;
import com.example.club.model.Message;
import com.example.club.model.MessageWithBLOBs;
import com.example.club.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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

    static Comparator<Message> comparator = new Comparator<Message>() {
        @Override
        public int compare(Message m1, Message m2) {
            if (Objects.equals(m1, m2)) {
                return 0;
            } else if (m1.getReleaseTime().before(m2.getReleaseTime())) {
                return 1;
            } else {
                return -1;
            }
        }
    };

    @Override
    public MessageWithBLOBs getMesssageById(Integer messageId) {
        return messageMapper.selectByPrimaryKey(messageId);
    }

    @Override
    public List<MessageWithBLOBs> getClubMessages(Integer clubId) {
        List<MessageWithBLOBs> result = messageMapper.selectByClubId(clubId);
        result.sort(comparator);
        return result;
    }

    @Override
    public List<MessageWithBLOBs> getMessageByAuthor(Integer userId) {
        List<MessageWithBLOBs> result = messageMapper.selectByAuthorId(userId);
        result.sort(comparator);
        return result;
    }
}
