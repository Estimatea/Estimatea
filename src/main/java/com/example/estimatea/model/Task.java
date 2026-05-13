package com.example.estimatea.model;
import java.time.LocalDate;

public class Task {
    private int taskId;
    private LocalDate startDate;
    private boolean completed;
    private String taskName;
    private LocalDate deadline;
    private int taskTime;
    private int taskPrice;
    private int projectId;
    private int subprojectId;
    private int currentComplexityId;

    public Task(int taskId, LocalDate startDate, boolean completed, String taskName, LocalDate deadline, int taskTime, int taskPrice, int projectId, int subprojectId, int currentComplexityId) {
        this.taskId = taskId;
        this.startDate = startDate;
        this.completed = completed;
        this.taskName = taskName;
        this.deadline = deadline;
        this.taskTime = taskTime;
        this.taskPrice = taskPrice;
        this.projectId = projectId;
        this.subprojectId = subprojectId;
        this.currentComplexityId = currentComplexityId;
    }

    public Task() {}

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

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getCurrentComplexityId() {
        return currentComplexityId;
    }

    public void setCurrentComplexityId(int currentComplexityId) {
        this.currentComplexityId = currentComplexityId;
    }
}
