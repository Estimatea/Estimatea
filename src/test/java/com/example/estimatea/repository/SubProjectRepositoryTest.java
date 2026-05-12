package com.example.estimatea.repository;
import com.example.estimatea.model.Project;
import com.example.estimatea.model.SubProject;
import com.example.estimatea.repository.jdbc.SubProjectRepository;
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

    @Test
    void shouldShowListOfSubProjects() {
        jdbc.update("INSERT INTO project (project_name, sum_time, sum_price, deadline, project_manager) VALUES ('Alpha Solutions Projekt Kalkulations Værktøj', 30, 25000, '2026-05-03', 1)");
        SubProject subProject = new SubProject("SubProjectRepoTest", LocalDate.of(2026, 5, 11), LocalDate.of(2026, 5, 27), false, 1);
        subProjectRepo.createSubProject(subProject);
        List<SubProject> subProjects = subProjectRepo.getAllSubProjects();
        assertNotNull(subProjects);
    }


}
