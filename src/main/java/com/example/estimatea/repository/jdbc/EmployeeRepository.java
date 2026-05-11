package com.example.estimatea.repository.jdbc;

import com.example.estimatea.repository.mapper.EmployeeMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class EmployeeRepository {

    private final JdbcTemplate jdbc;
    private final EmployeeMapper employeeMapper;

    // SQL STATEMENTS FOR project_employee Linked to a Project
    private final String ADD_EMPLOYEE_TO_PROJECT = "INSERT INTO project_employee_junction (employee_id, project_id) VALUES (?, ?)";
    private final String REMOVE_EMPLOYEE_FROM_PROJECT = "DELETE FROM project_employee WHERE project_employee_id = ?";

    // SQL STATEMENTS FOR sub_project_employee Linked to a Sub-Project
    private final String ADD_EMPLOYEE_TO_SUB_PROJECT = "INSERT INTO sub_project_employee_junction (project_employee_id, sub_id) VALUES (?, ?)";
    private final String REMOVE_EMPLOYEE_FROM_SUB_PROJECT = "DELETE FROM sub_project_employee_junction WHERE project_employee_id = ? AND sub_id = ?";


    public EmployeeRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.employeeMapper = employeeMapper;
    }



}
