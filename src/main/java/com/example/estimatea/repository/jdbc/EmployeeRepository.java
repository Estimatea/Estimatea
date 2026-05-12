package com.example.estimatea.repository.jdbc;
import com.example.estimatea.model.Employee;
import com.example.estimatea.model.Project;
import com.example.estimatea.repository.mapper.EmployeeMapper;
import com.example.estimatea.repository.mapper.ProjectEmployeeMapper;
import com.example.estimatea.repository.mapper.SubProjectEmployeeMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    private final JdbcTemplate jdbc;
    private final EmployeeMapper employeeMapper;
    private final SubProjectEmployeeMapper subEmployeeMapper;
    private final ProjectEmployeeMapper projectEmployeeMapper;

    // SQL STATEMENTS FOR project_employee Linked to a Project
    private final String ADD_EMPLOYEE_TO_PROJECT = "INSERT INTO project_employee (employee_id, project_id) VALUES (?, ?)";
    private final String REMOVE_EMPLOYEE_FROM_PROJECT = "DELETE FROM project_employee WHERE project_employee_id = ? AND project_id = ?";
    private final String GET_ALL_PROJECT_EMPLOYEES_FOR_PROJECT = "SELECT * FROM project_employee WHERE project_id = ?";

    //SQL statements for subproject employees
    // CHECK IN SERVICE THAT THEY ARE PROJECT EMPLOYEES

    private final String GET_ALL_EMPLOYEES_FOR_SUBPROJECT = "SELECT * FROM sub_project_employee WHERE sub_id = ?";
    private final String ASSIGN_EMPLOYEE_TO_SUBPROJECT = "INSERT INTO sub_project_employee VALUES (?,?)";
    private final String REMOVE_EMPLOYEE_FROM_SUBPROJECT = "DELETE FROM sub_project_employee WHERE project_employee_id = ? AND sub_id = ?";

    public EmployeeRepository(JdbcTemplate jdbc, EmployeeMapper employeeMapper, SubProjectEmployeeMapper subEmployeeMapper, ProjectEmployeeMapper projectEmployeeMapper) {
        this.jdbc = jdbc;
        this.employeeMapper = employeeMapper;
        this.subEmployeeMapper = subEmployeeMapper;
        this.projectEmployeeMapper = projectEmployeeMapper;
    }

    // CRUD QUERY'S For Main project
    //Adds en existing employee to a project
    public void addEmployeeToProject(Employee employee, Project project) {
        jdbc.update(ADD_EMPLOYEE_TO_PROJECT, employee.getEmployeeId(), project.getProjectId());
    }

    // removes an employee from project
    public void removeEmployeeFromProject(Employee employee, Project project) {
        jdbc.update(REMOVE_EMPLOYEE_FROM_PROJECT, employee.getEmployeeId(), project.getProjectId());
    }


    //returns a list of id's
    public List<Integer> getAllEmployeesForProject(Project project) {
        return jdbc.query(GET_ALL_PROJECT_EMPLOYEES_FOR_PROJECT, projectEmployeeMapper, project.getProjectId());
    }

    //CRUD Querys for subproject employees

    //returns only employee ID's
    //FIND ACTUAL EMPLOYEES IN SERVICE
    public List<Integer> getAllEmployeesForSubProject(int subProjectId) {
        return jdbc.query(GET_ALL_EMPLOYEES_FOR_SUBPROJECT, subEmployeeMapper, subProjectId);
    }

    //assigns an employee to the subproject
    //CHECK IN SERVICE THAT THEY ARE A PROJECT_EMPLOYEE
    public void assignEmployeeToSubProject(Employee employee, int subProjectId) {
        jdbc.update(ASSIGN_EMPLOYEE_TO_SUBPROJECT, employee.getEmployeeId(), subProjectId);
    }

    //Deletes a specific sub_project_employee from the subproject
    public void removeEmployeeFromSubProject(Employee employee, int subProjectId) {
        jdbc.update(REMOVE_EMPLOYEE_FROM_SUBPROJECT, employee.getEmployeeId(), subProjectId);
    }






}
