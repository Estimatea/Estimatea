package com.example.estimatea.repository;
import com.example.estimatea.repository.jdbc.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    JdbcTemplate jdbc;

    @Test
    void contextLoad() {}

    //verifies that it can access the h2 file
    @Test
    void checkH2schemaFile() {
        URL url = getClass().getClassLoader().getResource("h2init.sql");
        assertNotNull(url);
    }











//    @Test
//    void checkCreateProject() {
//        //Opretter Rolle først, da det skal bruges til employee
//        jdbc.update("INSERT INTO role(role_type,role_rate) VALUES ('Test Project Lead', 1500)");
//
//        //Opretter employee til at assigne til projekt manager
//        jdbc.update("INSERT INTO employee(employee_name,employee_username,employee_password, role_id) VALUES ('Test Employee', 'testeGutten', '112JegBrugesTilTest', 1)");
//
//        //Opretter projektet med vores medarbejder som projectManager
//        Project testProject = new Project("Test Project", LocalDate.now(), true, 100, 50, LocalDate.now().plusYears(1), 1);
//        projectRepository.createNewProject(testProject);
//        //Den får auto assigned id = 1 af H2
//        testProject.setProjectId(1);
//
//        //Kontrollerer hvorvidt den nye er oprettet
//
//        Project retrievedproject = projectRepository.findProjectById(testProject.getProjectId());
//        assertThat(retrievedproject).usingRecursiveComparison().isEqualTo(testProject);
//    }

}
