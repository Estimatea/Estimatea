package com.example.estimatea.repository;

import com.example.estimatea.repository.jdbc.ComplexityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ComplexityRepositoryTest {

    @Autowired
    private ComplexityRepository complexityRepo;

    @Autowired
    private JdbcTemplate jdbc;

    @Test
    void contextLoads() {}

    @Test
    void checkH2schemaFile() {
        URL url = getClass().getClassLoader().getResource("h2init.sql");
        System.out.println("URL= " + url);
    }

}
