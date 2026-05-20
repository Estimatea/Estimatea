package com.example.estimatea.service;

import com.example.estimatea.exception.NotFoundException;
import com.example.estimatea.model.SubProject;
import com.example.estimatea.repository.jdbc.SubProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubProjectService {

    private final SubProjectRepository subProjectRepository;

    public SubProjectService(SubProjectRepository subProjectRepository) {
        this.subProjectRepository = subProjectRepository;
    }

    public List<SubProject> listAllSubProjects() {
        List<SubProject> subprojects = subProjectRepository.getAllSubProjects();

        if (subprojects.isEmpty()) {
            throw new NotFoundException("No subprojects exists");
        }
        return subprojects;
    }

    public SubProject findSubProjectById(int subProjectId) {
        SubProject subProject = subProjectRepository.findSubProjectById(subProjectId);

        if (subProject == null) {
            throw new NotFoundException("No subproject with given id exists " + subProjectId);
        }
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
}
