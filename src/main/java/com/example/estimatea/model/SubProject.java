package com.example.estimatea.model;

import lombok.Setter;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
public class SubProject {
    private int subId;
    private String subName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadLine;

    private int sumTime;
    private int sumPrice;
    private boolean completed;
    private int projectId;

    public SubProject(int subId, String subName, LocalDate startDate, LocalDate deadLine, int sumTime, int sumPrice ,boolean completed, int projectId) {
        this.subId = subId;
        this.subName = subName;
        this.startDate = startDate;
        this.deadLine = deadLine;
        this.sumTime = sumTime;
        this.sumPrice = sumPrice;
        this.completed = completed;
        this.projectId = projectId;
    }

    public SubProject(String subName, LocalDate startDate, LocalDate deadLine, int sumTime, int sumPrice, boolean completed, int projectId) {
        this.subName = subName;
        this.startDate = startDate;
        this.deadLine = deadLine;
        this.sumTime = sumTime;
        this.sumPrice = sumPrice;
        this.completed = completed;
        this.projectId = projectId;
    }

    public SubProject() {}

    public int getSubId() {
        return subId;
    }

    public String getSubName() {
        return subName;
    }


    public int getProjectId() {
        return projectId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public int getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(int sumPrice) {
        this.sumPrice = sumPrice;
    }

    public int getSumTime() {
        return sumTime;
    }

    public void setSumTime(int sumTime) {
        this.sumTime = sumTime;
    }

    public boolean isCompleted() {
        return completed;
    }

}
