package com.example.estimatea.repository;


import com.example.estimatea.model.Employee;
import com.example.estimatea.model.Project;
import com.example.estimatea.repository.jdbc.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.net.URL;

@SpringBootTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JdbcTemplate jdbc;

    @Test
    void contextLoads() {}

    @Test
    void checkH2schemaFile() {
        URL url = getClass().getClassLoader().getResource("h2init.sql");
        System.out.println("URL= " + url);
    }

    @Test
    void addEmployeeToProjectTest() {

        jdbc.update("INSERT INTO role (role_type, role_rate) VALUES (?, ?)", "Developer", 100);

        jdbc.update("INSERT INTO employee (employee_name, employee_username, employee_password, role_id) VALUES (?, ?, ?, ?)",
                "John Doe", "johndoe", "password123", 1);

        jdbc.update("INSERT INTO project (project_name, start_date, completed, sum_time, sum_price, deadline, project_manager) VALUES (?, ?, ?, ?, ?, ?, ?)",
                "Test Project", "2024-01-01", false, 0, 0, "2024-12-31", 1);

        Employee employee = new Employee();
        employee.setEmployeeId(1);

        Project project = new Project();
        project.setProjectId(1);

        employeeRepository.addEmployeeToProject(employee, project);



    }

}
