package com.example.estimatea.repository;
import com.example.estimatea.model.SubProject;
import com.example.estimatea.repository.jdbc.SubProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class SubProjectRepositoryTest {

    @Autowired
    private SubProjectRepository subProjectRepo;

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
        assertThat(subProjects.size()).isEqualTo(2);
        assertThat(subProjects.getFirst().getSubName()).isEqualTo("Project calculation tool");
        assertThat(subProjects.getFirst().getDeadLine()).isEqualTo(LocalDate.of(2026,3,1));
    }

    @Test
    void shouldGetSubprojectsById() {
        List<SubProject> allSubProjects = subProjectRepo.getAllSubProjects();
        int subProjectId = allSubProjects.getFirst().getSubId();

        SubProject subProject = subProjectRepo.findSubProjectById(subProjectId); // GET_SUBPROJECT_BY_ID

        assertThat(subProject.getSubId()).isEqualTo(subProjectId);
        assertThat(subProject.completed()).isEqualTo(false);
        assertThat(subProject.getDeadLine()).isEqualTo(LocalDate.of(2026,3,1));
    }

    @Test
    void shouldGetSubprojectByProjectId() {
        int subProjectId = subProjectRepo.getAllSubProjects().getFirst().getProjectId();

        SubProject subprojectOne = subProjectRepo.findSubProjectById(subProjectId); // GET_SUB_PROJECT_BY_ID - Gets Subproject with ID 1

        assertThat(subprojectOne).isNotNull();
        assertThat(subprojectOne.getSubName()).isEqualTo("Project calculation tool");
    }

    @Test
    void shouldCreateSubProject() {
        SubProject subProject = new SubProject("Created sub project", /* Start_date */ LocalDate.of(2026, 2, 3), /* Deadline */ LocalDate.of(2026, 2, 8), false, 1);

        subProjectRepo.createSubProject(subProject);
        List<SubProject> allSubProjects = subProjectRepo.getAllSubProjects();

        assertThat(allSubProjects).hasSize(3);
        assertThat(allSubProjects).extracting(SubProject::getSubName).contains("Created sub project");
    }

    @Test
    void shouldDeleteSubProject() {
        List<SubProject> allSubProjects = subProjectRepo.getAllSubProjects();

        int initialSize = allSubProjects.size();
        int subProjectToDelete = allSubProjects.getFirst().getSubId();

        subProjectRepo.deleteSubProject(subProjectToDelete);

        List<SubProject> seededSubProjectsAfterDeletion = subProjectRepo.getAllSubProjects();

        assertThat(seededSubProjectsAfterDeletion).hasSize(initialSize - 1);
        assertThat(seededSubProjectsAfterDeletion).extracting(SubProject::getSubId).doesNotContain(subProjectToDelete);
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
