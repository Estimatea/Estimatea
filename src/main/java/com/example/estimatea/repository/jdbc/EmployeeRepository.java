package com.example.estimatea.repository.jdbc;
import com.example.estimatea.model.Employee;
import com.example.estimatea.repository.mapper.EmployeeMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    private final JdbcTemplate jdbc;
    private final EmployeeMapper employeeMapper;

        // SQL STATEMENTS for Employee Login Handling

    private final String EMPLOYEE_LOGIN = "SELECT * FROM employee WHERE employee_username = ? AND employee_password = ?";

        //SQL STATEMENTS FOR employees
    private final String GET_COMPANY_EMPLOYEE_LIST = "SELECT * FROM employee";

    // SQL STATEMENTS FOR project_employee Linked to a Project
    private final String GET_ALL_EMPLOYEES_FOR_PROJECT = "SELECT e.* FROM employee e " +
                                                         "JOIN project_employee pe ON e.employee_id = pe.employee_id " +
                                                         "WHERE pe.project_id = ?";

    // ADD & REMOVE employees to/from project
    private final String ADD_EMPLOYEE_TO_PROJECT = "INSERT INTO project_employee (employee_id, project_id) VALUES (?, ?)";
    private final String REMOVE_EMPLOYEE_FROM_PROJECT = "DELETE FROM project_employee WHERE project_employee_id = ? AND project_id = ?";

    // SQL statements for subproject employees
    private final String GET_ALL_EMPLOYEES_FOR_SUBPROJECT = "SELECT e.* FROM employee e " +
                                                            "JOIN project_employee pe ON e.employee_id = pe.employee_id " +
                                                            "JOIN sub_project_employee spe ON pe.project_employee_id = spe.project_employee_id " +
                                                            "WHERE spe.sub_id = ?" ;

    // ADD & REMOVE employees to/from Sub-project
    private final String ASSIGN_EMPLOYEE_TO_SUBPROJECT = "INSERT INTO sub_project_employee (project_employee_id, sub_id) VALUES (?,?)";
    private final String REMOVE_EMPLOYEE_FROM_SUBPROJECT = "DELETE FROM sub_project_employee WHERE project_employee_id = ? AND sub_id = ?";

    // RETRIEVE employees from project and subproject by id
    private final String GET_ALL_EMPLOYEES_BY_ID_FOR_SUBPROJECT = "SELECT project_employee_id FROM sub_project_employee WHERE sub_id = ?"; // RETRIEVE Sub-project employees ID's
    private final String GET_ALL_PROJECT_EMPLOYEES_IDS_FOR_PROJECT = "SELECT project_employee_id FROM project_employee WHERE project_id = ?"; // RETRIEVE Project employees ID's

    public EmployeeRepository(JdbcTemplate jdbc, EmployeeMapper employeeMapper) {
        this.jdbc = jdbc;
        this.employeeMapper = employeeMapper;
    }

    public Employee employeeLogin(String employeeUsername, String employeePassword) {
        List<Employee> employeeList = jdbc.query(EMPLOYEE_LOGIN, employeeMapper, employeeUsername, employeePassword);

        if (employeeList.isEmpty()) {
            return null;
        }
        return employeeList.getFirst();
    }

    public List<Employee> getAllEmployeesInCompany() {
        return jdbc.query(GET_COMPANY_EMPLOYEE_LIST, employeeMapper);
    }

        // CRUD QUERY's For Employees

    public List<Employee> getAllEmployeesForProject(int projectId) {
        return jdbc.query(GET_ALL_EMPLOYEES_FOR_PROJECT, employeeMapper, projectId);
    }

    public int addEmployeeToProject(int employeeId, int projectId) { // Adds an existing employee to a project
        return jdbc.update(ADD_EMPLOYEE_TO_PROJECT, employeeId, projectId);
    }

    public int removeEmployeeFromProject(int projectEmployeeId, int projectId) {  // Removes an employee from project
        return jdbc.update(REMOVE_EMPLOYEE_FROM_PROJECT, projectEmployeeId, projectId);
    }

        // CRUD QUREY's for SUB-PROJECT-Employees

    public List<Employee> getAllEmployeesForSubProject(int subProjectId) {
        return jdbc.query(GET_ALL_EMPLOYEES_FOR_SUBPROJECT, employeeMapper, subProjectId);
    }

    public int assignEmployeeToSubProject(int employeeId, int subProjectId) { // Assigns an employee to the subproject // CHECK IN SERVICE THAT THEY ARE A PROJECT_EMPLOYEE
        return jdbc.update(ASSIGN_EMPLOYEE_TO_SUBPROJECT, employeeId, subProjectId);
    }

    public int removeEmployeeFromSubProject(int employeeId, int subProjectId) { // Deletes a specific sub_project_employee from the subproject
        return jdbc.update(REMOVE_EMPLOYEE_FROM_SUBPROJECT, employeeId, subProjectId);
    }
}


