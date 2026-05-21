package com.example.estimatea.service;

import com.example.estimatea.exception.NotFoundException;
import com.example.estimatea.model.*;
import com.example.estimatea.repository.jdbc.EmployeeRepository;
import com.example.estimatea.repository.jdbc.RoleRepository;
import com.example.estimatea.repository.jdbc.SubProjectRepository;
import org.springframework.stereotype.Service;

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

        if (subprojects.isEmpty()) {
            throw new NotFoundException("No subprojects exists");
        }
//        for (SubProject subproject : subprojects) {
//            updateSubProjectScope(subproject);
//        }
        return subprojects;
    }

    public SubProject findSubProjectById(int subProjectId) {
        SubProject subProject = subProjectRepository.findSubProjectById(subProjectId);

        if (subProject == null) {
            throw new NotFoundException("No subproject with given id exists " + subProjectId);
        }
//        updateSubProjectScope(subProject);

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

//    public void updateSubProjectScope(SubProject subProject) {
//        updateSubProjectPrice(subProject.getSubId());
//        updateSubProjectTime(subProject.getSubId());
//
//
//    }

//    public void updateSubProjectPrice(int subProjectId) {
//        if (subProjectRepository.findSubProjectById(subProjectId) == null ) {
//            throw new NotFoundException("No subproject with given id exists " + subProjectId);
//        }
//
//        SubProject subProject = subProjectRepository.findSubProjectById(subProjectId);
//        subProject.setSumPrice(0);
//
//        List<Task> taskList = taskService.getTasksForSubprojectId(subProjectId);
//        for (Task task : taskList) {
//            Complexity taskComplexity = complexityService.getFromId(task.getTaskId());
//            double rate = taskComplexity.getRateMultiplier();
//            subProject.setSumPrice(subProject.getPrice + (rate * task.getTaskPrice()));
//        }
//
//        int roleMultiplier = 0;
//
//
//        List<Employee> subEmployeeList = employeeRepository.getAllEmployeesForSubProject(subProject.getSubId());
//        for (Employee e : subEmployeeList) {
//            roleMultiplier += roleRepository.getRoleById(e.getRoleId()).getRoleRate();
//        }
//
//
//        editSubProject(subProject);
//
//    }

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
