package com.example.club.model;

public class MessageWithBLOBs extends Message {
    private String title;

    private String contend;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContend() {
        return contend;
    }

    public void setContend(String contend) {
        this.contend = contend == null ? null : contend.trim();
    }
}