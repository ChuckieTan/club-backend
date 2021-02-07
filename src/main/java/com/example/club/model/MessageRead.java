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
}