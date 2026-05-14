package com.example.estimatea.repository.jdbc;
import com.example.estimatea.model.Employee;
import com.example.estimatea.model.Project;
import com.example.estimatea.repository.mapper.EmployeeMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    private final JdbcTemplate jdbc;
    private final EmployeeMapper employeeMapper;

        // SQL STATEMENTS FOR project_employee Linked to a Project
    private final String GET_ALL_PROJECT_EMPLOYEES_BY_ID_FOR_PROJECT = "SELECT * FROM project_employee WHERE project_id = ?";
    private final String GET_ALL_EMPLOYEES_FOR_PROJECT = "SELECT * FROM project_employee WHERE project_id = ?";

    // RETRIEVE BY ID's
    private final String ADD_EMPLOYEE_TO_PROJECT = "INSERT INTO project_employee (employee_id, project_id) VALUES (?, ?)";
    private final String REMOVE_EMPLOYEE_FROM_PROJECT = "DELETE FROM project_employee WHERE project_employee_id = ? AND project_id = ?";


        // SQL statements for subproject employees

    // CHECK IN SERVICE THAT THEY ARE PROJECT EMPLOYEES
    private final String GET_ALL_EMPLOYEES_BY_ID_FOR_SUBPROJECT = "SELECT * FROM sub_project_employee WHERE sub_id = ?";
    //private final String GET_ALL_EMPLOYEE_INFO_FOR_SUBPROJECT =;

    private final String ASSIGN_EMPLOYEE_TO_SUBPROJECT = "INSERT INTO sub_project_employee VALUES (?,?)";
    private final String REMOVE_EMPLOYEE_FROM_SUBPROJECT = "DELETE FROM sub_project_employee WHERE project_employee_id = ? AND sub_id = ?";

    public EmployeeRepository(JdbcTemplate jdbc, EmployeeMapper employeeMapper) {
        this.jdbc = jdbc;
        this.employeeMapper = employeeMapper;
    }

    // ROW-MAPPERS for retrieving ID's
    private final static RowMapper<Integer> ProjectEmpIdMapper = (rs, rowNum) -> rs.getInt("project_employee_id");
    private final static RowMapper<Integer> SubProjectEmpIdMapper = (rs, rowNum) -> rs.getInt("project_employee_id");

    // CRUD QUERY'S For Main PROJECT
    public List<Integer> getAllEmployeeIdsForProject(Project project) { // Returns a list of employee ID's connected to a project
        return jdbc.query(GET_ALL_PROJECT_EMPLOYEES_BY_ID_FOR_PROJECT, ProjectEmpIdMapper, project.getProjectId());
    }

    public List<Employee> getAllEmployeesForProject(Project project) {
        return jdbc.query(GET_ALL_EMPLOYEES_FOR_PROJECT, employeeMapper, project.getProjectId());
    }
    public void addEmployeeToProject(Employee employee, Project project) { // Adds an existing employee to a project
        jdbc.update(ADD_EMPLOYEE_TO_PROJECT, employee.getEmployeeId(), project.getProjectId());
    }

    public void removeEmployeeFromProject(Employee employee, Project project) {  // Removes an employee from project
        jdbc.update(REMOVE_EMPLOYEE_FROM_PROJECT, employee.getEmployeeId(), project.getProjectId());
    }

    // CRUD QUREY's for SUB_PROJECT employees
    public List<Integer> getAllEmployeesByIdForSubProject(int subProjectId) { // Returns a list of employee ID's connected to a Sub-Project
        return jdbc.query(GET_ALL_EMPLOYEES_BY_ID_FOR_SUBPROJECT, SubProjectEmpIdMapper, subProjectId);
    }

    public void assignEmployeeToSubProject(Employee employee, int subProjectId) { // Assigns an employee to the subproject // CHECK IN SERVICE THAT THEY ARE A PROJECT_EMPLOYEE
        jdbc.update(ASSIGN_EMPLOYEE_TO_SUBPROJECT, employee.getEmployeeId(), subProjectId);
    }

    public void removeEmployeeFromSubProject(Employee employee, int subProjectId) { // Deletes a specific sub_project_employee from the subproject
        jdbc.update(REMOVE_EMPLOYEE_FROM_SUBPROJECT, employee.getEmployeeId(), subProjectId);
    }
}
