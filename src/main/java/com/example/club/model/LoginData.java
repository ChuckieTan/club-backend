package com.example.club.model;

import java.util.Date;

public class LoginData {
    private Integer loginId;

    private Date time;

    private String ip;

    private Integer userId;

    private String device;

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device == null ? null : device.trim();
    }

    public LoginData(Integer userId, Date time, String ip, String device) {
        this.loginId = null;
        this.time = time;
        this.ip = ip;
        this.userId = userId;
        this.device = device;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "loginId=" + loginId +
                ", time=" + time +
                ", ip='" + ip + '\'' +
                ", userId=" + userId +
                ", device='" + device + '\'' +
                '}';
    }
}