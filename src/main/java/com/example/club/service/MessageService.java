package com.example.club.service;

import com.example.club.model.MessageWithBLOBs;

import java.util.List;

public interface MessageService {
    int postMessage(MessageWithBLOBs message);

    int updateMessage(MessageWithBLOBs message);

    int deleteMessage(Integer id);

    List<MessageWithBLOBs> getClubMessages(Integer clubId);

    MessageWithBLOBs getMesssageById(Integer messageId);
}
