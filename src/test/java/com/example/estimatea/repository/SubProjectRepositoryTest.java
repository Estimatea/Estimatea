package com.example.estimatea.repository;
import com.example.estimatea.model.SubProject;
import com.example.estimatea.repository.jdbc.SubProjectRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "/data-test.sql")
@Transactional
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
<<<<<<< Updated upstream
        assertNotNull(url);
=======
        System.out.println("URL= " + url);
    }

    @BeforeEach
    void setUp() {
        jdbc.update("INSERT INTO role(role_type,role_rate) VALUES ('Test Project Lead', 1500)");
        jdbc.update("INSERT INTO employee(employee_name,employee_username,employee_password, role_id) VALUES ('Test Employee', 'testeGutten', '112JegBrugesTilTest', 1)");
        jdbc.update("INSERT INTO project (project_name, sum_time, sum_price, deadline, project_manager) VALUES ('Alpha Solutions Projekt Kalkulations Værktøj', 30, 25000, '2026-05-03', 1)");
        jdbc.update("INSERT INTO subproject (sub_name, start_date, deadline, completed, project_id) VALUES ('Sub Project Test', '2026-02-02', '2026-02-28', false, 1)");
>>>>>>> Stashed changes
    }

    @Test
    void shouldShowListOfSubProjects() {
        List<SubProject> subProjects = subProjectRepo.getAllSubProjects(); // GET_ALL_SUBPROJECTS

        assertNotNull(subProjects);
        assertThat(subProjects.size()).isEqualTo(1);
        assertThat(subProjects.getFirst().getSubName()).isEqualTo("Sub Project Test");
        assertThat(subProjects.getFirst().getDeadLine()).isEqualTo("2026-02-28");
    }

    @Test
    void shouldGetSubprojectsById() {
        List<SubProject> allSubProjects = subProjectRepo.getAllSubProjects();
        int subProjectId = allSubProjects.getFirst().getSubId();

        SubProject subProject = subProjectRepo.getSubProjectById(subProjectId); // GET_SUBPROJECT_BY_ID

        assertThat(subProject.getSubId()).isEqualTo(subProjectId);
        assertThat(subProject.completed()).isEqualTo(false);
    }

    @Test
    void shouldGetSubprojectByProjectId() {
        jdbc.update("INSERT INTO subproject (sub_name, start_date, deadline, completed, project_id) VALUES ('Sub Project Test TWO', '2026-02-03', '2026-02-28', false, 1)");

        List<SubProject> subProjects = subProjectRepo.getSubProjectsByProjectId(1); // GET_SUBPROJECTS_BY_PROJECT_ID

        assertThat(subProjects.size()).isEqualTo(2);
        assertThat(subProjects.getFirst().getSubName()).isEqualTo("Sub Project Test");
        assertThat(subProjects.get(1).getSubName()).isEqualTo("Sub Project Test TWO");
    }

    @Test
    void shouldCreateSubProject() {
        SubProject subProject = new SubProject("Sub Project number TWO", /* Start_date */ LocalDate.of(2026, 2, 3), /* Deadline */ LocalDate.of(2026, 2, 8), false, 1);

        SubProject createdSubProject = subProjectRepo.createSubProject(subProject);

        List<SubProject> allSubProjects = subProjectRepo.getAllSubProjects();

        assertThat(allSubProjects.get(1).getSubName()).isEqualTo(createdSubProject.getSubName());
        assertThat(allSubProjects.get(0).getSubName()).isNotEqualTo(createdSubProject.getSubName());
    }

    @Test
    void shouldDeleteSubProject() {
        List<SubProject> allSubProjects = subProjectRepo.getAllSubProjects();
        int subProjectId = allSubProjects.getFirst().getSubId();

        subProjectRepo.deleteSubProject(subProjectId);

        List<SubProject> seededSubProjectsAfterDeletion = subProjectRepo.getAllSubProjects();

        assertThat(seededSubProjectsAfterDeletion.size()).isEqualTo(0);
    }

    @Test
    void shouldSetSubProjectCompleted() {
        List<SubProject> allSubProjects = subProjectRepo.getAllSubProjects();

        subProjectRepo.editSubProjectCompleted(true, 1);

        assertThat(allSubProjects.getFirst().completed()).isEqualTo(true);
    }
}
