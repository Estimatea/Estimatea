package com.example.estimatea.model;

import java.time.LocalDate;

public class Project {
    private int projectId;
    private String projectName;
    private int sumTime;
    private int sumPrice;
    private LocalDate startDate;
    private LocalDate deadLine;
    private int projectManager; // Foreign key from employee table
    private boolean completed;

    public Project(int projectId, String projectName, int sumTime, int sumPrice, LocalDate startDate ,LocalDate deadLine, int projectManager,  boolean completed) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.sumTime = sumTime;
        this.sumPrice = sumPrice;
        this.startDate = startDate;
        this.deadLine = deadLine;
        this.projectManager = projectManager;
        this.completed = completed;
    }

    public Project() {

    }

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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
