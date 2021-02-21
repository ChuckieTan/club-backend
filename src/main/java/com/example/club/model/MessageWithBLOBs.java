package com.example.club.model;

public class MessageWithBLOBs extends Message {
    private String title;

    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        return super.toString() + "MessageWithBLOBs{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}