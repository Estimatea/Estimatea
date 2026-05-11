package com.example.estimatea.model;

public class Complexity {

    private int estimateId;
    private int taskComplexity;
    private int taskId;

    public Complexity(int estimateId, int taskComplexity, int taskId) {
        this.estimateId = estimateId;
        this.taskComplexity = taskComplexity;
        this.taskId = taskId;
    }

    public Complexity() {}

    public int getTaskComplexity() {
        return taskComplexity;
    }

    public void setTaskComplexity(int taskComplexity) {
        this.taskComplexity = taskComplexity;
    }

    public int getEstimateId() {
        return estimateId;
    }

    public void setEstimateId(int estimateId) {
        this.estimateId = estimateId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
