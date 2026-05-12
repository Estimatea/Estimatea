package com.example.estimatea.model;

public class Complexity {

    private int estimateId;
    private int taskComplexity;

    public Complexity(int estimateId, int taskComplexity) {
        this.estimateId = estimateId;
        this.taskComplexity = taskComplexity;
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
}
