package com.example.estimatea.repository;

import com.example.estimatea.model.Task;
import com.example.estimatea.repository.jdbc.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class RoleRepositoryTest {

    @Autowired
    private TaskRepository roleRepository;

    @Autowired
    private JdbcTemplate jdbc;

    @Test
    void ContextLoads() {

    }

    @Test
    void checkH2schemaFile() {
        URL url = getClass().getClassLoader().getResource("h2init.sql");
        assertNotNull(url);
    }

//    @Test
//    void getAllRolesTest() {
//        Role role = roleRepository.ge
//    }

}
