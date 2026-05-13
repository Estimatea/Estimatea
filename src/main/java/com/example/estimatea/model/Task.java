package com.example.estimatea.model;

import java.time.LocalDate;

public class Task {
    private int taskId;
    private String taskName;
    private int taskTime;
    private int taskPrice;
    private LocalDate startDate;
    private LocalDate deadline;
    private int projectId;
    private int subprojectId;
    private boolean completed;
    //private Complexity complexity;

    public Task(int taskId, String taskName, int taskTime, int taskPrice, LocalDate startDate, LocalDate deadLine, int projectId, int subprojectId, boolean completed) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskTime = taskTime;
        this.taskPrice = taskPrice;
        this.startDate = startDate;
        this.deadline = deadLine;
        this.projectId = projectId;
        this.subprojectId = subprojectId;
        this.completed = completed;
    }

    public Task() {

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

//    public int getComplexityId() {
//        return complexityId;
//    }
//
//    public void setComplexityId(int complexityId) {
//        this.complexityId = complexityId;
//    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDeadLine() {
        return deadline;
    }

    public void setDeadLine(LocalDate deadline) {
        this.deadline = deadline;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getSubprojectId() {
        return subprojectId;
    }

    public void setSubprojectId(int subprojectId) {
        this.subprojectId = subprojectId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
