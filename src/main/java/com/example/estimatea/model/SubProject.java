package com.example.estimatea.model;

import java.time.LocalDate;

public class SubProject {
    private int subId;
    private String subName;
    private LocalDate startDate;
    private LocalDate deadLine;
    private boolean completed;
    private int projectId;

    public SubProject(String subName, LocalDate startDate, LocalDate deadLine, boolean completed, int projectId) {
        this.subName = subName;
        this.startDate = startDate;
        this.deadLine = deadLine;
        this.completed = completed;
        this.projectId = projectId;
    }

    public SubProject() {}

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

    public boolean completed() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
