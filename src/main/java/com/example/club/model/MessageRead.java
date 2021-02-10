package com.example.club.model;

import java.util.Date;

public class MessageRead extends MessageReadKey {
    private Date readTime;

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public MessageRead(Integer userId, Integer messageId, Date readTime) {
        super(userId, messageId);
        this.readTime = readTime;
    }
}