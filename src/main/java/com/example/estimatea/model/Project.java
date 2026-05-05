package com.example.estimatea.model;

import java.time.LocalDate;

public class Project {
    int projectId;
    String projectName;
    int sumTime;
    int sumPrice;
    LocalDate deadLine;
    Employee teamLead;

    public Project(int projectId, String projectName, int sumTime, int sumPrice, LocalDate deadLine, Employee teamLead) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.sumTime = sumTime;
        this.sumPrice = sumPrice;
        this.deadLine = deadLine;
        this.teamLead = teamLead;
    }

    public Project(){

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

    public Employee getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(Employee teamLead) {
        this.teamLead = teamLead;
    }
}
