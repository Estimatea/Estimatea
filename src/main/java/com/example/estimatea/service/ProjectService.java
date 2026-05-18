package com.example.estimatea.service;

import com.example.estimatea.exception.NotFoundException;
import com.example.estimatea.model.Project;
import com.example.estimatea.repository.jdbc.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> listAllProjects() {
        List<Project> projects = projectRepository.getAllProjects();

        if (projects.isEmpty()) {
            throw new NotFoundException("No projects exists");
        }
        return projects;
    }

    public void createNewProject(Project project) {
        if (project == null) {
            throw new NotFoundException("No project object received");
        }

        int rowsAffected = projectRepository.createNewProject(project);

        if (rowsAffected == 0) {
            throw new NotFoundException("No project was created " + project.getProjectId());
        }
    }

    public void findProjectById(int projectId) {
        Project project = projectRepository.getProjectById(projectId);

        if (project == null) {
            throw new NotFoundException("No project with given ID found " + projectId);
        }
    }

    public void editProject(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("No project object received");
        }

        int rowsAffected = projectRepository.editProject(project);

        if (rowsAffected == 0) {
            throw new NotFoundException("No project was updated " + project.getProjectId());
        }
    }

    public void deleteProject(int projectId) {
        int rowsAffected = projectRepository.deleteProject(projectId);

        if (rowsAffected == 0) {
            throw new NotFoundException("No project was deleted " + projectId);
        }
    }
}
