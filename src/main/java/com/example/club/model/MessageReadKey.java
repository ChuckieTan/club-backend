package com.example.club.model;

public class MessageReadKey {
    private Integer userId;

    private Integer messageId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public MessageReadKey(Integer userId, Integer messageId) {
        this.userId = userId;
        this.messageId = messageId;
    }
}