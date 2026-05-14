package com.example.estimatea.repository;
import com.example.estimatea.model.SubProject;
import com.example.estimatea.repository.jdbc.SubProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
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
        assertNotNull(url);
    }

    @Test
    void shouldShowListOfSubProjects() {
        List<SubProject> subProjects = subProjectRepo.getAllSubProjects(); // GET_ALL_SUBPROJECTS

        assertNotNull(subProjects);
        assertThat(subProjects.size()).isEqualTo(1);
        assertThat(subProjects.getFirst().getSubName()).isEqualTo("Project calculation tool");
        assertThat(subProjects.getFirst().getDeadLine()).isEqualTo(LocalDate.of(2026,3,1));
    }

    @Test
    void shouldGetSubprojectsById() {
        List<SubProject> allSubProjects = subProjectRepo.getAllSubProjects();
        int subProjectId = allSubProjects.getFirst().getSubId();

        SubProject subProject = subProjectRepo.getSubProjectById(subProjectId); // GET_SUBPROJECT_BY_ID

        assertThat(subProject.getSubId()).isEqualTo(subProjectId);
        assertThat(subProject.completed()).isEqualTo(false);
        assertThat(subProject.getDeadLine()).isEqualTo(LocalDate.of(2026,3,1));
    }

    @Test
    void shouldGetSubprojectByProjectId() {
        jdbc.update("INSERT INTO subproject (sub_id, sub_name, start_date, deadline, completed, project_id) " +
                "VALUES (2, 'Sub Project Test TWO', '2026-02-03', '2026-02-28', false, 1)");

        SubProject subprojectOne = subProjectRepo.getSubProjectById(1); // GET_SUB_PROJECT_BY_ID - Gets Subproject with ID 1
        SubProject subProjectTwo = subProjectRepo.getSubProjectById(2);

        assertThat(subprojectOne.getSubName()).isEqualTo("Project calculation tool");
        assertThat(subProjectTwo.getSubName()).isEqualTo("Sub Project Test TWO");
    }

    @Test
    void shouldCreateSubProject() {
        SubProject subProject = new SubProject("Created sub project", /* Start_date */ LocalDate.of(2026, 2, 3), /* Deadline */ LocalDate.of(2026, 2, 8), false, 1);

        SubProject createdSubProject = subProjectRepo.createSubProject(subProject);

        List<SubProject> allSubProjects = subProjectRepo.getAllSubProjects();

        assertThat(allSubProjects.get(1).getSubName()).isEqualTo(createdSubProject.getSubName());
        assertThat(allSubProjects.get(0).getSubName()).isNotEqualTo(createdSubProject.getSubName());
    }

    @Test
    void shouldDeleteSubProject() {
        int subProjectId = subProjectRepo.getAllSubProjects().getFirst().getSubId();

        subProjectRepo.deleteSubProject(subProjectId);

        List<SubProject> seededSubProjectsAfterDeletion = subProjectRepo.getAllSubProjects();
        assertThat(seededSubProjectsAfterDeletion.isEmpty());
    }

    @Test
    void shouldEditDeadline() {
        int subProjectId = subProjectRepo.getAllSubProjects().getFirst().getSubId();

        // OLD DEADLINE : '2026-03-01'
        subProjectRepo.editSubProjectDeadLine(LocalDate.of(2026, 3, 5), subProjectId);

        assertThat(subProjectRepo.getAllSubProjects().getFirst().getDeadLine()).isEqualTo(LocalDate.of(2026, 3, 5));
    }

    @Test
    void shouldSetSubProjectCompleted() {
        int subProjectId = subProjectRepo.getAllSubProjects().getFirst().getSubId();

        subProjectRepo.editSubProjectCompleted(true, subProjectId);

        assertThat(subProjectRepo.getAllSubProjects().getFirst().completed()).isTrue();
    }
}
