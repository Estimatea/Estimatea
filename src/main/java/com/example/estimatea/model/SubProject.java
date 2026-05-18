package com.example.estimatea.model;

import lombok.Setter;

import java.time.LocalDate;

@Setter
public class SubProject {
    private int subId;
    private String subName;
    private LocalDate startDate;
    private LocalDate deadLine;
    private boolean completed;
    private int projectId;

    public SubProject(int subId, String subName, LocalDate startDate, LocalDate deadLine, boolean completed, int projectId) {
        this.subId = subId;
        this.subName = subName;
        this.startDate = startDate;
        this.deadLine = deadLine;
        this.completed = completed;
        this.projectId = projectId;
    }

    public SubProject(String subName, LocalDate startDate, LocalDate deadLine, boolean completed, int projectId) {
        this.subName = subName;
        this.startDate = startDate;
        this.deadLine = deadLine;
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

    public boolean completed() {
        return completed;
    }

}
