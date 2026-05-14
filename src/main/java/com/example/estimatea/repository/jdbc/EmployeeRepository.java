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

        //SQL STATEMENTS FOR employees
    private final String GET_COMPANY_EMPLOYEE_LIST = "SELECT * FROM employee";

        // SQL STATEMENTS FOR project_employee Linked to a Project
    private final String GET_ALL_PROJECT_EMPLOYEES_BY_ID_FOR_PROJECT = "SELECT * FROM project_employee WHERE project_id = ?"; // RETRIEVE Project employees by ID's
    private final String GET_ALL_EMPLOYEES_FOR_PROJECT = "SELECT e.* FROM employee e " +
                                                         "JOIN project_employee pe ON e.employee_id = pe.employee_id " +
                                                         "WHERE pe.project_id = ?";

    // ADD & REMOVE employees to/from project
    private final String ADD_EMPLOYEE_TO_PROJECT = "INSERT INTO project_employee (employee_id, project_id) VALUES (?, ?)";
    private final String REMOVE_EMPLOYEE_FROM_PROJECT = "DELETE FROM project_employee WHERE project_employee_id = ? AND project_id = ?";

        // SQL statements for subproject employees
    // CHECK IN SERVICE THAT THEY ARE PROJECT EMPLOYEES
    private final String GET_ALL_EMPLOYEES_BY_ID_FOR_SUBPROJECT = "SELECT project_employee_id FROM sub_project_employee WHERE sub_id = ?"; // RETRIEVE Sub-project employees ID's
    private final String GET_ALL_EMPLOYEES_FOR_SUBPROJECT = "SELECT e.* FROM employee e " +
                                                            "JOIN project_employee pe ON e.employee_id = pe.employee_id " +
                                                            "JOIN sub_project_employee spe ON pe.project_employee_id = spe.project_employee_id " +
                                                            "WHERE spe.sub_id = ?" ;

    // ADD & REMOVE employees to/from Sub-project
    private final String ASSIGN_EMPLOYEE_TO_SUBPROJECT = "INSERT INTO sub_project_employee (project_employee_id, sub_id) VALUES (?,?)";
    private final String REMOVE_EMPLOYEE_FROM_SUBPROJECT = "DELETE FROM sub_project_employee WHERE project_employee_id = ? AND sub_id = ?";

    public EmployeeRepository(JdbcTemplate jdbc, EmployeeMapper employeeMapper) {
        this.jdbc = jdbc;
        this.employeeMapper = employeeMapper;
    }

        // ROW-MAPPERS for retrieving ID's
    private final static RowMapper<Integer> ProjectEmpIdMapper = (rs, rowNum) -> rs.getInt("project_employee_id"); // MAIN Project
    private final static RowMapper<Integer> SubProjectEmpIdMapper = (rs, rowNum) -> rs.getInt("project_employee_id"); // SUB - Project

        // CRUD QUERY's For Employees
    public List<Employee> getAllEmployeesInCompany() {
        return jdbc.query(GET_COMPANY_EMPLOYEE_LIST, employeeMapper);
    }

        // CRUD QUERY'S For Main PROJECT-Employees
    public List<Integer> getAllEmployeeIdsForProject(int projectId) { // Returns a list of employee ID's connected to a project
        return jdbc.query(GET_ALL_PROJECT_EMPLOYEES_BY_ID_FOR_PROJECT, ProjectEmpIdMapper, projectId);
    }

    public List<Employee> getAllEmployeesForProject(int projectId) {
        return jdbc.query(GET_ALL_EMPLOYEES_FOR_PROJECT, employeeMapper, projectId);
    }

    public void addEmployeeToProject(int employeeId, int projectId) { // Adds an existing employee to a project
        jdbc.update(ADD_EMPLOYEE_TO_PROJECT, employeeId, projectId);
    }

    public void removeEmployeeFromProject(int employeeId, int projectId) {  // Removes an employee from project
        jdbc.update(REMOVE_EMPLOYEE_FROM_PROJECT, employeeId, projectId);
    }

        // CRUD QUREY's for SUB-PROJECT-Employees
    public List<Integer> getAllEmployeesByIdForSubProject(int subProjectId) { // Returns a list of employee ID's connected to a Sub-Project
        return jdbc.query(GET_ALL_EMPLOYEES_BY_ID_FOR_SUBPROJECT, SubProjectEmpIdMapper, subProjectId);
    }

    public List<Employee> getAllEmployeesForSubProject(int subProjectId) {
        return jdbc.query(GET_ALL_EMPLOYEES_FOR_SUBPROJECT, employeeMapper, subProjectId);
    }

    public void assignEmployeeToSubProject(int employeeId, int subProjectId) { // Assigns an employee to the subproject // CHECK IN SERVICE THAT THEY ARE A PROJECT_EMPLOYEE
        jdbc.update(ASSIGN_EMPLOYEE_TO_SUBPROJECT, employeeId, subProjectId);
    }

    public void removeEmployeeFromSubProject(int employeeId, int subProjectId) { // Deletes a specific sub_project_employee from the subproject
        jdbc.update(REMOVE_EMPLOYEE_FROM_SUBPROJECT, employeeId, subProjectId);
    }
}
