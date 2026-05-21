package com.example.estimatea.service;

import com.example.estimatea.exception.NotFoundException;
import com.example.estimatea.model.SubProject;
import com.example.estimatea.model.Task;
import com.example.estimatea.repository.jdbc.SubProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubProjectService {

    private final SubProjectRepository subProjectRepository;
    private final TaskService taskService;

    public SubProjectService(SubProjectRepository subProjectRepository, TaskService taskService) {
        this.subProjectRepository = subProjectRepository;
        this.taskService = taskService;
    }

    public List<SubProject> listAllSubProjects() {
        List<SubProject> subprojects = subProjectRepository.getAllSubProjects();

        if (subprojects.isEmpty()) {
            throw new NotFoundException("No subprojects exists");
        }
        for (SubProject subproject : subprojects) {
            updateSubProjectScope(subproject.getSubId());
        }
        return subprojects;
    }

    public SubProject findSubProjectById(int subProjectId) {
        SubProject subProject = subProjectRepository.findSubProjectById(subProjectId);

        if (subProject == null) {
            throw new NotFoundException("No subproject with given id exists " + subProjectId);
        }
        updateSubProjectScope(subProjectId);

        return subProject;
    }

    public List<SubProject> findSubProjectsByProjectId(int projectId) {
        return subProjectRepository.findSubProjectsByProjectId(projectId);
    }

    public void createSubproject(SubProject subProject) {
        if (subProject == null) {
            throw new NotFoundException("No subproject object received");
        }

        int rowsAffected = subProjectRepository.createSubProject(subProject);

        if (rowsAffected == 0) {
            throw new NotFoundException("No subproject was created (ID)" + subProject);
        }
    }

    public void DeleteSubproject(int subprojectId) {
        int rowsAffected = subProjectRepository.deleteSubProject(subprojectId);

        if (rowsAffected == 0) {
            throw new NotFoundException("No subproject was deleted (ID)" + subprojectId);
        }
    }

    public void editSubProject(SubProject subProject) {
        if (subProject == null) {
            throw new IllegalArgumentException("No subproject object received");
        }

        int rowsAffected = subProjectRepository.editSubProject(subProject);

        if (rowsAffected == 0) {
            throw new NotFoundException("No project was updated " + subProject.getProjectId());
        }
    }

        // PRICE AND TIME ESTIMATION FOR SUBPROJECT

    public void updateSubProjectScope(int subProjectId) {
        updateSubProjectPrice(subProjectId);
        updateSubProjectTime(subProjectId);
    }

    public void updateSubProjectPrice(int subProjectId) {
        if (subProjectRepository.findSubProjectById(subProjectId) == null ) {
            throw new NotFoundException("No subproject with given id exists " + subProjectId);
        }

        SubProject subProject = subProjectRepository.findSubProjectById(subProjectId);
        subProject.setSumPrice(0);

        List<Task> taskList = taskService.getTasksForSubprojectId(subProjectId);
        for (Task task : taskList) {
            subProject.setSumPrice(subProject.getSumPrice() + task.getTaskPrice());
        }

        editSubProject(subProject);

    }

    public void updateSubProjectTime(int subProjectId) {
        if (subProjectRepository.findSubProjectById(subProjectId) == null ) {
            throw new NotFoundException("No subproject with given id exists " + subProjectId);
        }

        SubProject subProject = subProjectRepository.findSubProjectById(subProjectId);
        subProject.setSumTime(0);

        List<Task> taskList = taskService.getTasksForSubprojectId(subProjectId);
        for (Task task : taskList) {
            subProject.setSumTime(subProject.getSumTime() + task.getTaskTime());
        }

        editSubProject(subProject);

    }
}
