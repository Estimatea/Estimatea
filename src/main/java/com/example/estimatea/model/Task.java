package com.example.estimatea.model;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class Task {


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadLine;


    private int taskId;
    private boolean completed;
    private String taskName;
    private int taskTime;
    private int taskPrice;
    private int projectId;
    private int subprojectId;
    private int currentComplexityId;

    // Used to load object from database (Row Mapper)
    public Task(int taskId, LocalDate startDate, boolean completed, String taskName, LocalDate deadLine, int taskTime, int taskPrice, int projectId, int subprojectId, int currentComplexityId) {
        this.taskId = taskId;
        this.startDate = startDate;
        this.completed = completed;
        this.taskName = taskName;
        this.deadLine = deadLine;
        this.taskTime = taskTime;
        this.taskPrice = taskPrice;
        this.projectId = projectId;
        this.subprojectId = subprojectId;
        this.currentComplexityId = currentComplexityId;
    }

    // Used to create task objects
    public Task(LocalDate startDate, boolean completed, String taskName, LocalDate deadLine, int taskTime, int projectId, int subprojectId, int currentComplexityId) {
        this.startDate = startDate;
        this.completed = completed;
        this.taskName = taskName;
        this.deadLine = deadLine;
        this.taskTime = taskTime;
        this.projectId = projectId;
        this.subprojectId = subprojectId;
        this.currentComplexityId = currentComplexityId;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadline) {
        this.deadLine = deadline;
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
