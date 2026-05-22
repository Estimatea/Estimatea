package com.example.estimatea.repository;

import com.example.estimatea.model.Project;
import com.example.estimatea.repository.jdbc.ProjectRepository;
import com.example.estimatea.repository.jdbc.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    JdbcTemplate jdbc;
    @Autowired
    private TaskRepository taskRepository;

    @Test
    void contextLoad() {
    }

    @Test
    void checkH2schemaFile() {
        URL url = getClass().getClassLoader().getResource("h2init.sql");
        assertNotNull(url);
    }

    @Test
    void createNewProjectTest() {
        //Arrange
        Project project = new Project(LocalDate.of(2027, 1, 1), false, "Test Project", 0, 0, LocalDate.of(2027, 12, 31), 1);

        //Act
        projectRepository.createNewProject(project);

        //Assert
        List<Project> allProjects = projectRepository.getAllProjects();
        Project createdProject = allProjects.getLast();

        assertThat(createdProject.getStartDate()).isEqualTo(LocalDate.of(2027, 1, 1));
        assertThat(createdProject.isCompleted()).isEqualTo(false);
        assertThat(createdProject.getProjectName()).isEqualTo("Test Project");
        assertThat(createdProject.getSumTime()).isEqualTo(0);
        assertThat(createdProject.getSumPrice()).isEqualTo(0);
        assertThat(createdProject.getDeadLine()).isEqualTo(LocalDate.of(2027, 12, 31));
        assertThat(createdProject.getProjectManager()).isEqualTo(1);
    }

    @Test
    void getProjectByIdTest() {
        //Arrange
        int projectId = projectRepository.getAllProjects().getFirst().getProjectId();

        //Act
        Project project = projectRepository.getProjectById(projectId);

        //Assert
        assertThat(project.getProjectId()).isEqualTo(projectId);
        assertThat(project.getProjectName()).isEqualTo("Alpha Solutions");
    }

    @Test
    void editProjectTest() {
        //Arrange
        int projectId = projectRepository.getAllProjects().getFirst().getProjectId();

        //Act
        Project editedProject = new Project(LocalDate.of(2026, 1, 1), true, "Alpha Solutions", 200, 2500, LocalDate.of(2027, 1, 1), 1);
        editedProject.setProjectId(projectId);
        projectRepository.editProject(editedProject);

        //Assert
        Project project = projectRepository.getProjectById(projectId);
        assertThat(project.getStartDate()).isEqualTo(LocalDate.of(2026, 1, 1));
        assertThat(project.isCompleted()).isEqualTo(true);
        assertThat(project.getProjectName()).isEqualTo("Alpha Solutions");
        assertThat(project.getSumTime()).isEqualTo(200);
        assertThat(project.getSumPrice()).isEqualTo(2500);
        assertThat(project.getDeadLine()).isEqualTo(LocalDate.of(2027, 1, 1));
        assertThat(project.getProjectManager()).isEqualTo(1);
    }

    @Test
    void deleteProjectTest() {
        //Arrange
        int projectId = projectRepository.getAllProjects().getFirst().getProjectId();

        //Act
        projectRepository.deleteProject(projectId);

        //Assert
        assertThat(projectRepository.getAllProjects().size()).isEqualTo(1);
    }
}