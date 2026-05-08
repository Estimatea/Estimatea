package com.example.estimatea.model;

public class SubProject {
    private int subId;
    private String subName;
    private int projectId;

    public SubProject(int subId, String subName, int subTime, int subPrice, int projectId) {
        this.subId = subId;
        this.subName = subName;
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


    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

}
