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
        Task projectTask = new Task(LocalDate.of(2027, 1, 1), false, "Test Task", LocalDate.of(2027, 12, 1), 10, 0, 1, 1, 1);

        // Act
        taskRepository.createTaskForProject(projectTask);

        // Assert
        List<Task> projectTasks = taskRepository.getTasksByProjectId(1);
        assertThat(projectTasks.size()).isEqualTo(3);
        assertThat(projectTasks.getLast().getTaskName()).isEqualTo("Test Task");
        assertThat(projectTasks.getLast().getCompleted()).isEqualTo(false);
        assertThat(projectTasks.getLast().getStartDate()).isEqualTo(LocalDate.of(2027, 1, 1));
        assertThat(projectTasks.getLast().getDeadLine()).isEqualTo(LocalDate.of(2027, 12, 1));
        assertThat(projectTasks.getLast().getTaskTime()).isEqualTo(10);
        assertThat(projectTasks.getLast().getTaskPrice()).isEqualTo(100);
        assertThat(projectTasks.getLast().getProjectId()).isEqualTo(1);
        assertThat(projectTasks.getLast().getCurrentComplexityId()).isEqualTo(1);
    }

    @Test
    void createTaskForSubprojectTest() {
        // Arrange
        Task subprojectTask = new Task(LocalDate.of(2027, 1, 1), false, "Test SubprojectTask", LocalDate.of(2027, 12, 1), 50, 300, 1, 1, 1);

        // Act
        taskRepository.createTaskForSubproject(subprojectTask);

        // Assert
        List<Task> subprojectTasks = taskRepository.getTasksBySubprojectId(1);
        assertThat(subprojectTasks.size()).isEqualTo(3);
        assertThat(subprojectTasks.getLast().getTaskName()).isEqualTo("Test SubprojectTask");
        assertThat(subprojectTasks.getLast().getCompleted()).isEqualTo(false);
        assertThat(subprojectTasks.getLast().getStartDate()).isEqualTo(LocalDate.of(2027, 1, 1));
        assertThat(subprojectTasks.getLast().getDeadLine()).isEqualTo(LocalDate.of(2027, 12, 1));
        assertThat(subprojectTasks.getLast().getTaskTime()).isEqualTo(50);
        assertThat(subprojectTasks.getLast().getTaskPrice()).isEqualTo(300);
        assertThat(subprojectTasks.getLast().getProjectId()).isEqualTo(1);
        assertThat(subprojectTasks.getLast().getSubprojectId()).isEqualTo(1);
        assertThat(subprojectTasks.getLast().getCurrentComplexityId()).isEqualTo(1);
    }

    @Test
    void editTaskTest() {
        // Arrange
        int taskId = taskRepository.getTasksByProjectId(1).getFirst().getTaskId();

        // Act
        Task editedTask = new Task(LocalDate.of(2027, 12, 12), true, "Edited Task", LocalDate.of(2028, 1, 1), 20, 200, 1, 1, 5);
        editedTask.setTaskId(taskId);
        taskRepository.editTask(editedTask);

        // Assert
        Task result = taskRepository.getTaskById(taskId);
        assertThat(result.getTaskName()).isEqualTo("Edited Task");
        assertThat(result.getCompleted()).isEqualTo(true);
        assertThat(result.getStartDate()).isEqualTo(LocalDate.of(2027, 12, 12));
        assertThat(result.getDeadLine()).isEqualTo(LocalDate.of(2028, 1, 1));
        assertThat(result.getTaskTime()).isEqualTo(20);
        assertThat(result.getTaskPrice()).isEqualTo(200);
        assertThat(result.getCurrentComplexityId()).isEqualTo(5);
    }

    @Test
    void deleteTaskTest() {
        // Arrange
        int taskId = taskRepository.getTasksByProjectId(1).getFirst().getTaskId();

        // Act
       taskRepository.deleteTask(taskId);

        // Assert
        assertThat(taskRepository.getTasksByProjectId(1).size()).isEqualTo(1);
    }

    @Test
    void completeTaskTest() {
        // Arrange
        int taskId = taskRepository.getTasksByProjectId(1).getFirst().getTaskId();

        // Act
        Task taskToComplete = new Task();
        taskToComplete.setTaskId(taskId);
        taskRepository.completeTask(taskId);

        // Assert
        assertThat(taskRepository.getTaskById(taskId).getCompleted()).isEqualTo(true);
    }

    @Test
    void getTaskByIdTest() {
        //Arrange
        int taskId = taskRepository.getTasksByProjectId(1).getFirst().getTaskId();

        //Act
        Task task = taskRepository.getTaskById(taskId);

        //Assert
        assertThat(task.getTaskId()).isEqualTo(taskId);
        assertThat(task.getTaskName()).isEqualTo("Alpha Task One");
    }

    @Test
    void getTasksByProjectIdTest() {
        // Arrange
        List<Task> projectTasks = taskRepository.getTasksByProjectId(1);
        int firstTaskId = projectTasks.getFirst().getTaskId();
        int secondTaskId = projectTasks.getLast().getTaskId();

        // Act
        Task taskOne = taskRepository.getTaskById(firstTaskId);
        Task taskTwo = taskRepository.getTaskById(secondTaskId);

        // Assert
        assertThat(taskOne.getTaskName()).isEqualTo("Alpha Task One");
        assertThat(taskTwo.getTaskName()).isEqualTo("Alpha Task Two");
    }

    @Test
    void getTasksBySubprojectId() {
        // Arrange
        List<Task> subprojectTasks = taskRepository.getTasksBySubprojectId(1);
        int firstTaskId = subprojectTasks.getFirst().getTaskId();
        int secondTaskId = subprojectTasks.getLast().getTaskId();

        // Act
        Task taskOne = taskRepository.getTaskById(firstTaskId);
        Task taskTwo = taskRepository.getTaskById(secondTaskId);

        // Assert
        assertThat(taskOne.getTaskName()).isEqualTo("Alpha Task One");
        assertThat(taskTwo.getTaskName()).isEqualTo("Alpha Task Two");
    }

    @Test
    void updateComplexityScoreTest() {
        //Arrange
        Task task = taskRepository.getTasksByProjectId(1).getFirst();
        int taskId = task.getTaskId();

        //Act
        taskRepository.updateComplexityScore(10, taskId);

        //Assert
        Task updatedTask = taskRepository.getTaskById(taskId);
        assertThat(updatedTask.getCurrentComplexityId()).isEqualTo(10);
    }

}
