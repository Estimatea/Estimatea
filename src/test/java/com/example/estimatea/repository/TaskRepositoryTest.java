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
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private JdbcTemplate jdbc;

    @Test
    void contextLoads() {
    }

    @Test
    void checkH2schemaFile() {
        URL url = getClass().getClassLoader().getResource("h2init.sql");
        assertNotNull(url);
    }

    @Test
    void createTaskForProjectTest() {
        // Arrange
        Task projectTask = new Task(LocalDate.of(2027, 1, 1), false, "Test Task", LocalDate.of(2027, 12, 1), 10, 100, 1, 0, 2, 1);

        // Act
        taskRepository.createTaskForProject(projectTask);

        List<Task> projectTasks = taskRepository.getTasksByProjectId(1);

        // Assert
        assertThat(projectTasks.size()).isEqualTo(1);
        assertThat(projectTasks.getFirst().getStartDate()).isEqualTo(LocalDate.of(2027, 1, 1));
        assertThat(projectTasks.getFirst().getCompleted()).isEqualTo(false);
        assertThat(projectTasks.getFirst().getTaskName()).isEqualTo("Test Task");
        assertThat(projectTasks.getFirst().getDeadLine()).isEqualTo(LocalDate.of(2027, 12, 1));
        assertThat(projectTasks.getFirst().getTaskTime()).isEqualTo(10);
        assertThat(projectTasks.getFirst().getTaskPrice()).isEqualTo(100);
        assertThat(projectTasks.getFirst().getProjectId()).isEqualTo(1);
        assertThat(projectTasks.getFirst().getSubprojectId()).isEqualTo(0);
        assertThat(projectTasks.getFirst().getEmployeeId()).isEqualTo(2);
        assertThat(projectTasks.getFirst().getCurrentComplexityId()).isEqualTo(1);

    }

    @Test
    void createTaskForSubprojectTest() {
        //Arrange
        Task subprojectTask = new Task(LocalDate.of(2027, 1, 1), false, "Test SubprojectTask", LocalDate.of(2027, 12, 1), 50, 300, 1, 1, 2, 1);

        //Act
        taskRepository.createTaskForSubproject(subprojectTask);

        //Assert
        List<Task> subprojectTasks = taskRepository.getTasksBySubprojectId(1);

        assertThat(subprojectTasks.size()).isEqualTo(1);
        assertThat(subprojectTasks.getFirst().getStartDate()).isEqualTo(LocalDate.of(2027, 1, 1));
        assertThat(subprojectTasks.getFirst().getCompleted()).isEqualTo(false);
        assertThat(subprojectTasks.getFirst().getTaskName()).isEqualTo("Test SubprojectTask");
        assertThat(subprojectTasks.getFirst().getDeadLine()).isEqualTo(LocalDate.of(2027, 12, 1));
        assertThat(subprojectTasks.getFirst().getTaskTime()).isEqualTo(50);
        assertThat(subprojectTasks.getFirst().getTaskPrice()).isEqualTo(300);
        assertThat(subprojectTasks.getFirst().getProjectId()).isEqualTo(1);
        assertThat(subprojectTasks.getFirst().getSubprojectId()).isEqualTo(1);
        assertThat(subprojectTasks.getFirst().getEmployeeId()).isEqualTo(2);
        assertThat(subprojectTasks.getFirst().getCurrentComplexityId()).isEqualTo(1);

    }

    @Test
    void editTaskTest() {
        // Arrange
        Task projectTask = new Task(LocalDate.of(2027, 1, 1), false, "Test Task", LocalDate.of(2027, 12, 1), 10, 100, 1, 0, 2, 5);
        taskRepository.createTaskForProject(projectTask);
        int actualId = taskRepository.getTasksByProjectId(1).getFirst().getTaskId();



        //Act
        Task editedTask = new Task(LocalDate.of(2027, 12, 12), true, "Edited Task", LocalDate.of(2028, 1, 1), 20, 200, 1, 0, 3, 5);
        editedTask.setTaskId(actualId);
        taskRepository.editTask(editedTask);

        //Assert
        List<Task> editedTasks = taskRepository.getTasksByProjectId(1);
        assertThat(editedTasks.size()).isEqualTo(1);
        assertThat(editedTasks.getFirst().getStartDate()).isEqualTo(LocalDate.of(2027, 12, 12));
        assertThat(editedTasks.getFirst().getCompleted()).isEqualTo(true);
        assertThat(editedTasks.getFirst().getTaskName()).isEqualTo("Edited Task");
        assertThat(editedTasks.getFirst().getDeadLine()).isEqualTo(LocalDate.of(2028, 1, 1));
        assertThat(editedTasks.getFirst().getTaskTime()).isEqualTo(20);
        assertThat(editedTasks.getFirst().getTaskPrice()).isEqualTo(200);
        assertThat(editedTasks.getFirst().getProjectId()).isEqualTo(1);
        assertThat(editedTasks.getFirst().getSubprojectId()).isEqualTo(0);
        assertThat(editedTasks.getFirst().getEmployeeId()).isEqualTo(3);
        assertThat(editedTasks.getFirst().getCurrentComplexityId()).isEqualTo(5);
    }


}
