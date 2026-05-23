package com.example.estimatea.service;

import com.example.estimatea.exception.NotFoundException;
import com.example.estimatea.model.*;
import com.example.estimatea.repository.jdbc.EmployeeRepository;
import com.example.estimatea.repository.jdbc.RoleRepository;
import com.example.estimatea.repository.jdbc.SubProjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubProjectService {

    private final SubProjectRepository subProjectRepository;
    private final TaskService taskService;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final ComplexityService complexityService;

    public SubProjectService(SubProjectRepository subProjectRepository, TaskService taskService,  EmployeeRepository employeeRepository, RoleRepository roleRepository,  ComplexityService complexityService) {
        this.subProjectRepository = subProjectRepository;
        this.taskService = taskService;
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.complexityService = complexityService;
    }

    public List<SubProject> listAllSubProjects() {
        List<SubProject> subprojects = subProjectRepository.getAllSubProjects();
        List<SubProject> updatedSubprojects = new ArrayList<>();

        if (subprojects.isEmpty()) {
            throw new NotFoundException("No subprojects exists");
        }

        for (SubProject subproject : subprojects) {
            updatedSubprojects.add(updateSubProjectScope(subproject));
        }
        return updatedSubprojects;
    }

    public SubProject findSubProjectById(int subProjectId) {
        SubProject subProject = subProjectRepository.findSubProjectById(subProjectId);

        if (subProject == null) {
            throw new NotFoundException("No subproject with given id exists " + subProjectId);
        }


        return updateSubProjectScope(subProject);
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

    // CHECKs if all Tasks on a Subproject is Completed
    public void completeSubProject(int subProjectId) {

        List<Task> subProjectTasks = taskService.getTasksForSubprojectId(subProjectId);

        for (Task subTasks : subProjectTasks) {

            if (!subTasks.getCompleted()) {
                throw new IllegalArgumentException("Cannot complete Subproject: Subproject tasks is still incomplete");
            }
        }

       int rowsAffected =  subProjectRepository.completeSubProject(subProjectId);

        if (rowsAffected == 0) {
            throw new NotFoundException(("Subproject was not set to complete " + subProjectId));
        }
    }


    // PRICE AND TIME ESTIMATION FOR SUBPROJECT

    public SubProject updateSubProjectScope(SubProject subProject) {
        List<Task> tasks = taskService.getTasksForSubprojectId(subProject.getSubId());
        if (!tasks.isEmpty()) {
            updateSubProjectPriceAndTime(subProject.getSubId(), tasks);
        }
        return subProjectRepository.findSubProjectById(subProject.getSubId());
    }

    public void updateSubProjectPriceAndTime(int subProjectId, List<Task> tasks) {
        SubProject subProject = subProjectRepository.findSubProjectById(subProjectId);
        if (subProject == null) {
            throw new NotFoundException("No subproject with given id exists " + subProjectId);
        }
        subProject.setSumPrice(0);
        subProject.setSumTime(0);

        List<Task> updatedTasks = new ArrayList<>();
        for (Task t : tasks) {
            updatedTasks.add(taskService.taskPriceCalculatorForTaskInSubProject(t));
        }

        for (Task t : updatedTasks) {
            subProject.setSumPrice(subProject.getSumPrice() + t.getTaskPrice());
            subProject.setSumTime(subProject.getSumTime() + t.getTaskTime());
        }

        editSubProject(subProject);
    }
}
