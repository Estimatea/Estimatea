package com.example.estimatea.service;
import com.example.estimatea.exception.NotFoundException;
import com.example.estimatea.model.Employee;
import com.example.estimatea.repository.jdbc.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployeeInCompany() { // Retrieve all employees in organization
        return employeeRepository.getAllEmployeesInCompany();
    }

        // MAIN PROJECT EMPLOYEES
    public List<Employee> getAllEmployeesByProjectId(int projectId) { // Retrieve all employees and their info assigned to a given project (id)
        List<Employee> projectEmployees = employeeRepository.getAllEmployeesForProject(projectId);

            if (projectEmployees.isEmpty()) {
                throw new NotFoundException("No employees found on given project: " + projectId);
            }

            return projectEmployees;
    }

    public void addEmployeeToProject(int employeeId, int projectId) { // Adds employee to project and check if ID exists in database
        int rowsAffected = employeeRepository.addEmployeeToProject(employeeId, projectId);

        if (rowsAffected == 0) {
            throw new NotFoundException("Employee not found " + projectId + " EMP: " + employeeId);
        }
    }

    public void removeEmployeeFromProject(int employeeId, int projectId) { // Removes employee from project and checks if ID exists in database
        int rowsAffected = employeeRepository.removeEmployeeFromProject(employeeId, projectId);

        if (rowsAffected == 0) {
            throw new NotFoundException("Employee not found " + projectId + " EMP: " + employeeId);
        }
    }

        // SUBPROJECT EMPLOYEES
    public void addEmployeeToSubProject(int employeeId, int subProject) {
       // boolean isProjectEmployee = employeeRepository.getAllEmployeesForProject()

        int rowsAffected = employeeRepository.assignEmployeeToSubProject(employeeId, subProject);

        if (rowsAffected == 0) {
            throw new NotFoundException("Employee not found " + subProject + " EMP: " + employeeId);
        }
    }

    public void removeEmployeeFromSubProject(int employeeId, int subProjectId) { // Removes employee from project and checks if ID exists in database
        int rowsAffected = employeeRepository.removeEmployeeFromSubProject(employeeId, subProjectId);

        if (rowsAffected == 0) {
            throw new NotFoundException("Employee not found " + subProjectId + " EMP: " + employeeId);
        }
    }

    public List<Employee> getAllEmployeesForSubproject(int subProject) { // Retrieves list of employees and their given information assigned to their ID
        List<Employee> allEmployeesOnSubProject = employeeRepository.getAllEmployeesForSubProject(subProject);

        if (allEmployeesOnSubProject.isEmpty()) {
            throw new NotFoundException("No employees found on given subproject " + subProject);
        }

        return allEmployeesOnSubProject;
    }


    //    public List<Integer> allEmployeesIdsBySubProject(int subProjectId) { // Retrieves list of employees IDS ONLY coupled to a subproject
//        List<Integer> subProjectEmployeesIds = employeeRepository.getAllEmployeesIdsForSubProject(subProjectId);
//
//        if (subProjectEmployeesIds.isEmpty()) {
//            throw new NotFoundException("No employees found on given subproject: " + subProjectId);
//        }
//
//        return subProjectEmployeesIds;
//    }

    //    public List<Integer> getAllEmployeesIdsByProjectId(int projectId) { // Retrieve all employees (IDS ONLY) assigned to a given project (id)
//        List<Integer> projectEmployeeIds = employeeRepository.getAllEmployeeIdsForProject(projectId);
//
//        if (projectEmployeeIds.isEmpty()) {
//            throw new NotFoundException("No employees found on given project: " + projectId);
//        }
//
//        return projectEmployeeIds;
//    }












}
