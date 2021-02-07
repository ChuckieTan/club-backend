package com.example.club.model;

public class Image {
    private Integer imageId;

    private String path;

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Image() {
    }

    public Image(Integer imageId, String path) {
        this.imageId = imageId;
        this.path = path;
    }
}