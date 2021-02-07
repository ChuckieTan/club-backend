package com.example.club.mapper;

import com.example.club.model.Message;
import com.example.club.model.MessageWithBLOBs;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer messageId);

    int insert(MessageWithBLOBs record);

    int insertSelective(MessageWithBLOBs record);

    MessageWithBLOBs selectByPrimaryKey(Integer messageId);

    int updateByPrimaryKeySelective(MessageWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MessageWithBLOBs record);

    int updateByPrimaryKey(Message record);
}