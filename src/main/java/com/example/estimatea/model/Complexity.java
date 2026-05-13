package com.example.estimatea.model;

public class Complexity {

    private int complexityId;
    private int complexityScore;
    private int labelType;
    private double rateMultiplier;

    public Complexity(int complexityId, int complexityScore, int labelType, double rateMultiplier) {
        this.complexityId = complexityId;
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

    public int getLabelType() {
        return labelType;
    }

    public void setLabelType(int labelType) {
        this.labelType = labelType;
    }

    public double getRateMultiplier() {
        return rateMultiplier;
    }

    public void setRateMultiplier(double rateMultiplier) {
        this.rateMultiplier = rateMultiplier;
    }
}
