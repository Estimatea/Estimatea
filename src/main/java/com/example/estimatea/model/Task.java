package com.example.estimatea.model;

import java.time.LocalDate;

public class Task {
    private int taskId;
    private String taskName;
    private int taskTime;
    private int taskPrice;
    private int projectId;
    private int ressourceId;
    private LocalDate startDate;
    private LocalDate deadLine;
    private boolean completed;

    public Task(int taskId, String taskName, int taskTime, int taskPrice, int projectId, int ressourceId,  LocalDate startDate, LocalDate deadLine, boolean completed) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskTime = taskTime;
        this.taskPrice = taskPrice;
        this.projectId = projectId;
        this.ressourceId = ressourceId;
        this.startDate = startDate;
        this.deadLine = deadLine;
    }

    public Task () {
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(int taskTime) {
        this.taskTime = taskTime;
    }

    public int getTaskPrice() {
        return taskPrice;
    }

    public void setTaskPrice(int taskPrice) {
        this.taskPrice = taskPrice;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getRessourceId() {
        return ressourceId;
    }

    public void setRessourceId(int ressourceId) {
        this.ressourceId = ressourceId;
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
