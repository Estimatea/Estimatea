package com.example.estimatea.repository;

import com.example.estimatea.model.Employee;
import com.example.estimatea.model.Project;
import com.example.estimatea.model.Role;
import com.example.estimatea.repository.jdbc.ProjectRepository;
import com.example.estimatea.repository.jdbc.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.time.LocalDate;

@SpringBootTest
@Transactional
@ActiveProfiles("Test")
public class ProjektRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;
    JdbcTemplate jdbc;

    @Test
    void contextLoad() {}

    //verifies that it can access the h2 file
    @Test
    void checkH2schemaFile() {
        URL url = getClass().getClassLoader().getResource("h2init.sql");
        System.out.println("URL= " + url);
    }

    //tests whether it can create a project
    @Test
    void checkCreateProject() {
        //Opretter Rolle først, da det skal bruges til employee
        jdbc.update("INSERT INTO role(role_type,role_rate) VALUES (\"Test Projekt Lead\", 1500)");

        //Opretter employee til at assigne til projekt manager
        jdbc.update("INSERT INTO employee(employee_name,employee_username,employee_password, role_id) VALUES (\"Test Employee\", \"testeGutten\", \"112JegBrugesTilTest\", 1)");

        //Opretter projektet med vores medarbejder som projectManager
        Project testProject = new Project("Test Projekt", LocalDate.now(), true, 100, 50, LocalDate.now().plusYears(1), 1);
        projectRepository.createNewProject(testProject);

        //Kontrollerer

    }

}
