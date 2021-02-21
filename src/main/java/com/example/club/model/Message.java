package com.example.club.model;

import java.util.Date;

public class Message {
    private Integer messageId;

    private Date releaseTime;

    private Integer clubId;

    private Boolean draftMark;

    private Integer type;

    private Integer authorId;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public Boolean getDraftMark() {
        return draftMark;
    }

    public void setDraftMark(Boolean draftMark) {
        this.draftMark = draftMark;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", releaseTime=" + releaseTime +
                ", clubId=" + clubId +
                ", draftMark=" + draftMark +
                ", type=" + type +
                ", authorId=" + authorId +
                '}';
    }

}