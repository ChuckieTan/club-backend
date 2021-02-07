package com.example.club.model;

public class ClubWithBLOBs extends Club {
    private String introduction;

    private String coreValues;

    private String plans;

    private String practicallyAnalysis;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getCoreValues() {
        return coreValues;
    }

    public void setCoreValues(String coreValues) {
        this.coreValues = coreValues == null ? null : coreValues.trim();
    }

    public String getPlans() {
        return plans;
    }

    public void setPlans(String plans) {
        this.plans = plans == null ? null : plans.trim();
    }

    public String getPracticallyAnalysis() {
        return practicallyAnalysis;
    }

    public void setPracticallyAnalysis(String practicallyAnalysis) {
        this.practicallyAnalysis = practicallyAnalysis == null ? null : practicallyAnalysis.trim();
    }
}