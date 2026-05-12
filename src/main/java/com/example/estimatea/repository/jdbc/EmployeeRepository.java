package com.example.estimatea.repository.jdbc;
import com.example.estimatea.model.Employee;
import com.example.estimatea.model.Project;
import com.example.estimatea.model.SubProject;
import com.example.estimatea.repository.mapper.EmployeeMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {

    private final JdbcTemplate jdbc;
    private final EmployeeMapper employeeMapper;

    // SQL STATEMENTS FOR project_employee Linked to a Project
    private final String ADD_EMPLOYEE_TO_PROJECT = "INSERT INTO project_employee (employee_id, project_id) VALUES (?, ?)";
    private final String REMOVE_EMPLOYEE_FROM_PROJECT = "DELETE FROM project_employee WHERE project_employee_id = ? AND project_id = ?";

    // SQL STATEMENTS FOR sub_project_employee Linked to a Sub-Project
    private final String ADD_EMPLOYEE_TO_SUB_PROJECT = "INSERT INTO sub_project_employee_junction (project_employee_id, sub_id) VALUES (?, ?)";
    private final String REMOVE_EMPLOYEE_FROM_SUB_PROJECT = "DELETE FROM sub_project_employee_junction WHERE project_employee_id = ? AND sub_id = ?";

    public EmployeeRepository(JdbcTemplate jdbc, EmployeeMapper employeeMapper) {
        this.jdbc = jdbc;
        this.employeeMapper = employeeMapper;
    }

    // CRUD QUERY'S For Main project
    public void addEmployeeToProject(Employee employee, Project project) {
        jdbc.update(ADD_EMPLOYEE_TO_PROJECT, employee.getEmployeeId(), project.getProjectId());
    }

    public void removeEmployeeFromProject(Employee employee, Project project) {
        jdbc.update(REMOVE_EMPLOYEE_FROM_PROJECT, employee.getEmployeeId(), project.getProjectId());
    }

    // CRUD QUERY'S For Subproject
    public void addEmployeeToSubProject(Employee employee, SubProject subProject) {
        jdbc.update(ADD_EMPLOYEE_TO_SUB_PROJECT, employee.getEmployeeId(), subProject.getProjectId());
    }

    public void removeEmployeeFromSubProject(Employee employee, SubProject subProject) {
        jdbc.update(REMOVE_EMPLOYEE_FROM_SUB_PROJECT, employee.getEmployeeId(), subProject.getProjectId());
    }





}
