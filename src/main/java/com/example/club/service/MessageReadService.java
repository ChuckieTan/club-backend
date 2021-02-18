package com.example.club.service;

import com.example.club.model.MessageRead;

import java.util.List;

public interface MessageReadService {
    void initMessage(List<Integer> userIds, Integer messageId);

    List<MessageRead> getUserMessages(Integer userId);

    void deleteMessage(Integer messageId);

    int readMessage(Integer userId, Integer messageId);
}
