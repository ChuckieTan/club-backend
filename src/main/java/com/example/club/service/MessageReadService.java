package com.example.club.service;

import com.example.club.model.MessageReturn;

import java.util.List;

public interface MessageReadService {
    void initMessage(Integer clubId, Integer messageId);

    List<MessageReturn> getUserMessages(Integer userId);
}
