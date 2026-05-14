package com.example.estimatea.model;

public class Complexity {

    private int complexityId;
    private int complexityScore;
    private String labelType;
    private double rateMultiplier;

    // Used to load object from database (Row mapper)
    public Complexity(int complexityId, int complexityScore, String labelType, double rateMultiplier) {
        this.complexityId = complexityId;
        this.complexityScore = complexityScore;
        this.labelType = labelType;
        this.rateMultiplier = rateMultiplier;
    }

    // Used to created Complexity scores
    public Complexity(int complexityScore, String labelType, double rateMultiplier) {
        this.complexityScore = complexityScore;
        this.labelType = labelType;
        this.rateMultiplier = rateMultiplier;
    }

    public Complexity() {}

    public int getComplexityId() {
        return complexityId;
    }

    public void setComplexityId(int complexityId) {
        this.complexityId = complexityId;
    }

    public int getComplexityScore() {
        return complexityScore;
    }

    public void setComplexityScore(int complexityScore) {
        this.complexityScore = complexityScore;
    }

    public String getLabelType() {
        return labelType;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }

    public double getRateMultiplier() {
        return rateMultiplier;
    }

    public void setRateMultiplier(double rateMultiplier) {
        this.rateMultiplier = rateMultiplier;
    }
}
