package com.example.estimatea.service;
import com.example.estimatea.exception.DuplicateKeyException;
import com.example.estimatea.exception.NotFoundException;
import com.example.estimatea.model.Employee;
import com.example.estimatea.repository.jdbc.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Employee Login
    public Employee employeeLogin(String employeeUsername, String employeePassword) {
        Employee employee = employeeRepository.employeeLogin(employeeUsername, employeePassword);

        if (employee == null) {
            throw new NotFoundException("Invalid email or password");
        }
        return employee;
    }

    // Employees in database
    public List<Employee> getAllEmployeeInCompany() { // Retrieve all employees in organization
        List<Employee> employees = employeeRepository.getAllEmployeesInCompany();

        if (employees.isEmpty()) {
            throw new NotFoundException("No employees exists");
        }
        return employees;
    }

    // MAIN PROJECT EMPLOYEES
    public List<Employee> getAllEmployeesByProjectId(int projectId) { // Retrieve all employees and their info assigned to a given project (id)
        return employeeRepository.getAllEmployeesForProject(projectId);
    }

    public List<Employee> employeesNotInProject(int projectId) {
        List<Employee> all = getAllEmployeeInCompany();
        List<Employee> inProject = getAllEmployeesByProjectId(projectId);

        List<Employee> notInProject = new ArrayList<>();
        boolean found;

        for (Employee employee : all) {
            found = false;
            for (Employee employeeInProject : inProject) {
                if (employee.getEmployeeId() == employeeInProject.getEmployeeId()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                notInProject.add(employee);
            }
        }
        return notInProject;
    }

    public void addEmployeeToProject(int employeeId, int projectId) { // Adds employee to project and check if ID exists in database
        List<Employee> alreadyAssignedEmployees = employeeRepository.getAllEmployeesForProject(projectId);

        for (Employee employee : alreadyAssignedEmployees) {
            if (employee.getEmployeeId() == employeeId) {
                throw new DuplicateKeyException("Employee with id " + employeeId + " already assigned to project " + projectId);
            }
        }

        // Adds employee to Main project
        int rowsAffected = employeeRepository.addEmployeeToProject(employeeId, projectId);


        if (rowsAffected == 0) {
            throw new NotFoundException("Employee not assigned to project: " + projectId + " EMP: " + employeeId);
        }
    }

    public void removeEmployeeFromProject(int employeeId, int projectId) { // Removes employee from project and checks if ID exists in database
        List<Employee> employeesOnProject = employeeRepository.getAllEmployeesForProject(projectId);
        boolean employeeFound = false;

        for (Employee e : employeesOnProject) {

            if (e.getEmployeeId() == employeeId) {
                employeeFound = true;
                break;
            }
        }

        if (!employeeFound) {
            throw new IllegalArgumentException("No employee with given id exists on project " + projectId);
        }

        int rowsAffected = employeeRepository.removeEmployeeFromProject(employeeId, projectId);

        if (rowsAffected == 0) {
            throw new NotFoundException("Employee not removed from projectID: " + projectId + ", with employeeID: " + employeeId);
        }
    }

    // SUBPROJECT EMPLOYEES

    public List<Employee> getAllEmployeeViableToAddToSubProject(int subprojectID, int projectID) {
        List<Employee> projectEmployees = getAllEmployeesByProjectId(projectID);
        List<Employee> currentSubProjectEmployees = getAllEmployeesForSubproject(subprojectID);
        List<Employee> viableToAddList = new ArrayList<>();
        boolean found;

        for (Employee pE : projectEmployees) {
            found = false;
            for (Employee sPE : currentSubProjectEmployees) {
                if (pE.getEmployeeId() == sPE.getEmployeeId()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                viableToAddList.add(pE);
            }
        }
        return viableToAddList;

    }

    public List<Employee> getAllEmployeesForSubproject(int subProject) { // Retrieves list of employees and their given information assigned to their ID
        return employeeRepository.getAllEmployeesForSubProject(subProject);
    }

    public void addEmployeeToSubProject(int projectId, int employeeId, int subProject) {
        List<Employee> currentProject = employeeRepository.getAllEmployeesForProject(projectId);
        boolean isEmpOnMainProject = false;

        for (Employee e : currentProject) {

            if (e.getEmployeeId() == employeeId) {
                isEmpOnMainProject = true;
                break;
            }
        }

        if (!isEmpOnMainProject) {
            throw new IllegalArgumentException("Invalid assignment - employee not found on MAIN project " + projectId);
        }

        // Adds employee to subProject
        int rowsAffected = employeeRepository.assignEmployeeToSubProject(employeeId, subProject);

        if (rowsAffected == 0) {
            throw new NotFoundException("Employee not found " + projectId + " EMP: " + employeeId);
        }
    }

    public void removeEmployeeFromSubProject(int employeeId, int subProjectId) { // Removes employee from project and checks if ID exists in database
        List<Employee> currentSubProject = employeeRepository.getAllEmployeesForSubProject(subProjectId);
        boolean isEmpOnSubProject = false;

        for (Employee e : currentSubProject) {

            if (e.getEmployeeId() == employeeId) {
                isEmpOnSubProject = true;
                break;
            }
        }

        if (!isEmpOnSubProject) {
            throw new IllegalArgumentException("Invalid removal - employee not to be found on Subproject with ID: " + subProjectId);
        }

        int rowsAffected = employeeRepository.removeEmployeeFromSubProject(employeeId, subProjectId);

        if (rowsAffected == 0) {
            throw new NotFoundException("Employee not found " + subProjectId + " EMP: " + employeeId);
        }
    }
}
