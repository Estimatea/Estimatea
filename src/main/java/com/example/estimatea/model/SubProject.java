package com.example.estimatea.model;

import java.time.LocalDate;

public class SubProject {
    private int subId;
    private String subName;
    private int projectId;
    private LocalDate startDate;
    private LocalDate deadLine;
    private boolean completed;

    public SubProject(int subId, String subName, int projectId, LocalDate startDate, LocalDate deadLine, boolean completed) {
        this.subId = subId;
        this.subName = subName;
        this.projectId = projectId;
        this.startDate = startDate;
        this.deadLine = deadLine;
        this.completed = completed;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
