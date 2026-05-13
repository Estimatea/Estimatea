package com.example.estimatea.repository;


import com.example.estimatea.repository.jdbc.EmployeeRepository;
import com.example.estimatea.repository.jdbc.ProjectRepository;
import com.example.estimatea.repository.jdbc.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.net.URL;

@SpringBootTest
@ActiveProfiles("test")
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private JdbcTemplate jdbc;

    @Test
    void contextLoads() {}

    @Test
    void checkH2schemaFile(){
        URL url = getClass().getClassLoader().getResource("h2init.sql");
        System.out.println("URL= " + url);



    }
}
