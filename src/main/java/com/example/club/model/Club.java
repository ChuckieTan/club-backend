package com.example.club.model;

public class Club {
    private Integer clubId;

    private String name;

    private Integer avatarId;

    private Integer type;

    private String consultantName;

    private String consultantTel;

    private Integer initiatorId;

    private Integer progress;

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Integer avatarId) {
        this.avatarId = avatarId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName == null ? null : consultantName.trim();
    }

    public String getConsultantTel() {
        return consultantTel;
    }

    public void setConsultantTel(String consultantTel) {
        this.consultantTel = consultantTel == null ? null : consultantTel.trim();
    }

    public Integer getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(Integer initiatorId) {
        this.initiatorId = initiatorId;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "Club{" +
                "clubId=" + clubId +
                ", name='" + name + '\'' +
                ", avatarId=" + avatarId +
                ", type=" + type +
                ", consultantName='" + consultantName + '\'' +
                ", consultantTel='" + consultantTel + '\'' +
                ", initiatorId=" + initiatorId +
                ", progress=" + progress +
                '}';
    }
}