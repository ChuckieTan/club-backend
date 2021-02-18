package com.example.club.model;

import java.util.Date;

public class ClubMember extends ClubMemberKey {
    private Integer role;

    private Boolean isAgree;

    private Date applyTime;

    private Integer grade;

    private String introduction;

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Boolean getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(Boolean isAgree) {
        this.isAgree = isAgree;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public ClubMember() {
    }

    public ClubMember(Integer role,
                      Boolean isAgree,
                      Date applyTime,
                      Integer grade,
                      String introduction) {
        this.role = role;
        this.isAgree = isAgree;
        this.applyTime = applyTime;
        this.grade = grade;
        this.introduction = introduction;
    }
}