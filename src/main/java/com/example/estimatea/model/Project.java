package com.example.estimatea.model;

import java.time.LocalDate;

public class Project {
    private int projectId;
    private String projectName;
    private int sumTime;
    private int sumPrice;
    private LocalDate deadLine;
    private int projectManager; // Foreign key from employee table

    public Project(int projectId, String projectName, int sumTime, int sumPrice, LocalDate deadLine, int projectManager) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.sumTime = sumTime;
        this.sumPrice = sumPrice;
        this.deadLine = deadLine;
        this.projectManager = projectManager;
    }

    public Project() {}

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getSumTime() {
        return sumTime;
    }

    public void setSumTime(int sumTime) {
        this.sumTime = sumTime;
    }

    public int getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(int sumPrice) {
        this.sumPrice = sumPrice;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public int getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(int projectManager) {
        this.projectManager = projectManager;
    }
}
