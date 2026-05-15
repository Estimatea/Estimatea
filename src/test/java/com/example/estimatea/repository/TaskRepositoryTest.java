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

        // Assert
        List<Task> projectTasks = taskRepository.getTasksByProjectId(1);
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
        int taskId = taskRepository.getTasksByProjectId(1).getFirst().getTaskId();

        //Act
        Task editedTask = new Task(LocalDate.of(2027, 12, 12), true, "Edited Task", LocalDate.of(2028, 1, 1), 20, 200, 1, 0, 3, 5);
        editedTask.setTaskId(taskId);
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

    @Test
    void deleteTaskTest() {
        //Arrange
        Task task = new Task(LocalDate.of(2027, 1, 1), false, "Test Task", LocalDate.of(2027, 12, 1), 10, 100, 1, 0, 2, 5);
        taskRepository.createTaskForProject(task);
        int taskId = taskRepository.getTasksByProjectId(1).getFirst().getTaskId();

        //Act
        Task taskToDelete = new Task();
        taskToDelete.setTaskId(taskId);
        taskRepository.deleteTask(taskToDelete);

        //Assert
        List<Task> tasks = taskRepository.getTasksByProjectId(1);
        assertThat(tasks.size()).isEqualTo(0);
    }

    @Test
    void completeTaskTest() {
        //Arrange
        Task task = new Task(LocalDate.of(2027, 1, 1), false, "Test Task", LocalDate.of(2027, 12, 1), 10, 100, 1, 0, 2, 5);
        taskRepository.createTaskForProject(task);
        int taskId = taskRepository.getTasksByProjectId(1).getFirst().getTaskId();

        //Act
        Task taskToComplete = new Task();
        taskToComplete.setTaskId(taskId);
        taskRepository.completeTask(taskToComplete);

        //Assert
        List<Task> tasks = taskRepository.getTasksByProjectId(1);
        assertThat(tasks.getFirst().getCompleted()).isEqualTo(true);
    }

    @Test
    void getTaskByIdTest() {
        //Arrange
        jdbc.update("INSERT INTO task (task_id, start_date, completed, task_name, deadline, task_time, task_price, project_id, employee_id, current_complexity_id) " +
                "VALUES (1, '2027-01-01', false, 'Task One', '2027-12-01', 10, 100, 1, 2, 5)");
        jdbc.update("INSERT INTO task (task_id, start_date, completed, task_name, deadline, task_time, task_price, project_id, employee_id, current_complexity_id) " +
                "VALUES (2, '2027-01-01', false, 'Task Two', '2027-12-01', 10, 100, 1, 2, 5)");

        //Act
        Task taskOne = taskRepository.getTaskById(1);
        Task taskTwo = taskRepository.getTaskById(2);

        //Assert
        assertThat(taskOne.getTaskName()).isEqualTo("Task One");
        assertThat(taskTwo.getTaskName()).isEqualTo("Task Two");
    }

    @Test
    void getTasksByProjectIdTest() {
        //Arrange
        jdbc.update("INSERT INTO task (start_date, completed, task_name, deadline, task_time, task_price, project_id, employee_id, current_complexity_id) " +
                "VALUES ('2027-01-01', false, 'Task One', '2027-12-01', 10, 100, 1, 2, 5)");
        jdbc.update("INSERT INTO task (start_date, completed, task_name, deadline, task_time, task_price, project_id, employee_id, current_complexity_id) " +
                "VALUES ('2027-01-01', false, 'Task Two', '2027-12-01', 10, 100, 1, 2, 5)");

        // Task from another project to test if it gets task from right project
        jdbc.update("INSERT INTO task (start_date, completed, task_name, deadline, task_time, task_price, project_id, employee_id, current_complexity_id) " +
                "VALUES ('2027-01-01', false, 'Task Two', '2027-12-01', 10, 100, 2, 2, 5)");

        //Act
        List<Task> projectTasks = taskRepository.getTasksByProjectId(1);

        //Assert
        assertThat(projectTasks.size()).isEqualTo(2);
        assertThat(projectTasks.get(0).getTaskName()).isEqualTo("Task One");
        assertThat(projectTasks.get(1).getTaskName()).isEqualTo("Task Two");
    }

    @Test
    void getTasksBySubprojectId() {
        //Arrange
        jdbc.update("INSERT INTO task (start_date, completed, task_name, deadline, task_time, task_price, project_id, subproject_id, employee_id, current_complexity_id) " +
                "VALUES ('2027-01-01', false, 'Task One', '2027-12-01', 10, 100, 1, 1, 2, 5)");
        jdbc.update("INSERT INTO task (start_date, completed, task_name, deadline, task_time, task_price, project_id, subproject_id, employee_id, current_complexity_id) " +
                "VALUES ('2027-01-01', false, 'Task Two', '2027-12-01', 10, 100, 1, 1, 2, 5)");

        // Task from another project to test if it gets task from right project
        jdbc.update("INSERT INTO task (start_date, completed, task_name, deadline, task_time, task_price, project_id, subproject_id, employee_id, current_complexity_id) " +
                "VALUES ('2027-01-01', false, 'Task Two', '2027-12-01', 10, 100, 2, 2, 2, 5)");

        //Act
        List<Task> subprojectTasks = taskRepository.getTasksBySubprojectId(1);

        //Assert
        assertThat(subprojectTasks.size()).isEqualTo(2);
        assertThat(subprojectTasks.get(0).getTaskName()).isEqualTo("Task One");
        assertThat(subprojectTasks.get(1).getTaskName()).isEqualTo("Task Two");
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
