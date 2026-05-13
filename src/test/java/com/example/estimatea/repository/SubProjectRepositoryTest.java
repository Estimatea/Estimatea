package com.example.estimatea.repository;
import com.example.estimatea.model.Project;
import com.example.estimatea.model.SubProject;
import com.example.estimatea.repository.jdbc.SubProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class SubProjectRepositoryTest {

    @Autowired
    private SubProjectRepository subProjectRepo;

    @Autowired
    private JdbcTemplate jdbc;

    @Test
    void contextLoads() {}

    @Test
    void checkH2schemaFile() {
        URL url = getClass().getClassLoader().getResource("h2init.sql");
        System.out.println("URL= " + url);
    }

    @BeforeEach
    void setUp() {
        jdbc.update("INSERT INTO role(role_type,role_rate) VALUES ('Test Projekt Lead', 1500)");
        jdbc.update("INSERT INTO employee(employee_name,employee_username,employee_password, role_id) VALUES ('Test Employee', 'testeGutten', '112JegBrugesTilTest', 1)");
        jdbc.update("INSERT INTO project (project_name, sum_time, sum_price, deadline, project_manager) VALUES ('Alpha Solutions Projekt Kalkulations Værktøj', 30, 25000, '2026-05-03', 1)");
        jdbc.update("INSERT INTO subproject (sub_name, start_date, deadline, completed, project_id) VALUES ('Sub Project Test', 2026-02-02, 2026-02-28, false, 1)");
    }

    @Test
    void shouldShowListOfSubProjects() {
        List<SubProject> subProjects = subProjectRepo.getAllSubProjects();
        assertNotNull(subProjects);
    }  


}
