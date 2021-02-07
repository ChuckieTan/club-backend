package com.example.club.mapper;

import com.example.club.model.MessageRead;
import com.example.club.model.MessageReadKey;

public interface MessageReadMapper {
    int deleteByPrimaryKey(MessageReadKey key);

    int insert(MessageRead record);

    int insertSelective(MessageRead record);

    MessageRead selectByPrimaryKey(MessageReadKey key);

    int updateByPrimaryKeySelective(MessageRead record);

    int updateByPrimaryKey(MessageRead record);
}