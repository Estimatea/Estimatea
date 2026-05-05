package com.example.estimatea.model;

public class SubProject {
    int subId;
    String subName;
    int subTime;
    int subPrice;
    int projectId;

    public SubProject(int subId, String subName, int subTime, int subPrice, int projectId) {
        this.subId = subId;
        this.subName = subName;
        this.subTime = subTime;
        this.subPrice = subPrice;
        this.projectId = projectId;
    }

    public SubProject() {

    }

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public int getSubTime() {
        return subTime;
    }

    public void setSubTime(int subTime) {
        this.subTime = subTime;
    }

    public int getSubPrice() {
        return subPrice;
    }

    public void setSubPrice(int subPrice) {
        this.subPrice = subPrice;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

}
