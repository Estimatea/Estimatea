package com.example.estimatea.model;

public class Task {
    int taskId;
    String taskName;
    int taskTime;
    int taskPrice;
    Project project;
    Subproject subProject;
    Ressource ressource;

    public Task(int taskId, String taskName, int taskTime, int taskPrice, Project project, Subproject subProject, Ressource ressource) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskTime = taskTime;
        this.taskPrice = taskPrice;
        this.project = project;
        this.subProject = subProject;
        this.ressource = ressource;
    }

    public Task(Project project) {
        this.project = project;
    }

    public Task(Subproject subProject) {
        this.subProject = subProject;
    }

    public Task () {

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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Subproject getSubProject() {
        return subProject;
    }

    public void setSubProject(Subproject subProject) {
        this.subProject = subProject;
    }

    public Ressource getRessource() {
        return ressource;
    }

    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }
}
